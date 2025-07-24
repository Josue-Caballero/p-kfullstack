import { redirect } from "next/navigation";
import { cookies } from "next/headers";
import Header from "@/shared/Header";
import DashboardComponent from "@/ui/components/DashboardComponent";
import { getDashboardData } from "@/infraestructure/dashboard/dashboardService";

export default async function DashboardPage() {
  console.log("[Dashboard] Iniciando DashboardPage...");

  const cookieStore = await cookies();
  const token = cookieStore.get("token")?.value;
  console.log(
    "[Dashboard] Token extraído:",
    token ? "TOKEN_ENCONTRADO" : "NO_TOKEN"
  );

  if (!token) {
    console.log("[Dashboard] No hay token, redirigiendo a login...");
    redirect("/login");
  }

  // Obtener datos del dashboard en el servidor (SSR)
  console.log("[Dashboard] Obteniendo datos del servidor...");
  const dashboardData = await getDashboardData();

  if (!dashboardData) {
    console.log(
      "[Dashboard] No se pudieron obtener datos, redirigiendo a login"
    );
    redirect("/login");
  }

  console.log("[Dashboard] Token válido, renderizando dashboard con datos SSR");
  return (
    <>
      <Header token={token} />
      <DashboardComponent
        userData={dashboardData.userData}
        projects={dashboardData.projects}
      />
    </>
  );
}
