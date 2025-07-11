### ---------- Build Stage ----------
FROM node:20-alpine AS builder
WORKDIR /app

COPY frontend/package*.json ./
RUN npm ci --silent

#  Vite backend URL'ini build-time değişkeni olarak geçiriyoruz
ARG VITE_BACKEND_URL=http://localhost:8070/api/v1
ENV VITE_BACKEND_URL=$VITE_BACKEND_URL
COPY frontend ./
RUN npm run build

### ---------- Runtime Stage ----------
FROM nginx:1.27-alpine
# basit ve güvenli nginx config
RUN rm /etc/nginx/conf.d/default.conf
COPY <<'NGINX' /etc/nginx/conf.d/site.conf
server {
    listen 80;
    root   /usr/share/nginx/html;
    index  index.html;
    location / {
        try_files $uri $uri/ /index.html;
    }
}
NGINX

COPY --from=builder /app/dist /usr/share/nginx/html
