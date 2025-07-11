import { useEffect, useState } from "react";

export default function useDarkMode() {
  const [enabled, setEnabled] = useState(
    () => localStorage.theme === "dark" || window.matchMedia("(prefers-color-scheme: dark)").matches
  );

  useEffect(() => {
    document.documentElement.classList.toggle("dark", enabled);
    localStorage.theme = enabled ? "dark" : "light";
  }, [enabled]);

  return [enabled, setEnabled] as const;
}
