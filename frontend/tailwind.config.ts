import type { Config } from "tailwindcss";

const config: Config = {
  content: [
    "./index.html",
    "./src/**/*.{ts,tsx}",   // tüm React + TS dosyaları
  ],
  darkMode: "class",         // body/html'e .dark sınıfı ekleyerek geçiş
  theme: {
    extend: {
      // Marka rengi tek yerde
      colors: {
        primary: "rgb(var(--color-primary) / <alpha-value>)",
      },
      fontFamily: {
        sans: ["Inter", "sans-serif"],
      },
    },
  },
  plugins: [],
};

export default config;
