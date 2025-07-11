import useDarkMode from "../hooks/useDarkMode";
import { SunIcon, MoonIcon } from "@heroicons/react/24/outline";

export default function Header() {
  const [dark, setDark] = useDarkMode();

  return (
    <header className="flex items-center justify-between py-4">
      <h1 className="text-2xl font-semibold tracking-tight">
        Nearby <span className="text-primary">Locator</span>
      </h1>

      <button
        onClick={() => setDark(!dark)}
        className="rounded-full p-2 transition hover:bg-gray-200 dark:hover:bg-gray-700"
      >
        {dark ? <SunIcon className="h-5 w-5" /> : <MoonIcon className="h-5 w-5" />}
      </button>
    </header>
  );
}
