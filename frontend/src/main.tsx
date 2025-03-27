import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import MainScreen from "./screens/MainScreen";
import { Provider } from "./components/ui/provider";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";

const client = new QueryClient();

createRoot(document.getElementById("root")!).render(
  <StrictMode>
    <Provider>
      <QueryClientProvider client={client}>
        <MainScreen />
      </QueryClientProvider>
    </Provider>
  </StrictMode>
);
