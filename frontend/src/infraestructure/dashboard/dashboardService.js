import { cookies } from "next/headers";
import { getProjects } from "@/infraestructure/projects/projectService";

export async function getDashboardData() {
  console.log("[DashboardService] Obteniendo datos del dashboard...");

  const cookieStore = await cookies();
  const token = cookieStore.get("token")?.value;

  if (!token) {
    console.log("[DashboardService] No hay token disponible");
    return null;
  }

  try {
    // Obtener proyectos del usuario
    const projects = await getProjects();

    // Obtener datos del usuario desde el primer proyecto (si existe)
    let userData = {
      id: 1, // Fallback por defecto
      username: "admin",
      email: "admin@kruger.com",
    };

    // Si hay proyectos, obtener datos del owner del primer proyecto
    if (projects && projects.length > 0 && projects[0].owner) {
      userData = {
        id: projects[0].owner.id,
        username: projects[0].owner.username,
        email: projects[0].owner.email,
      };
    }

    console.log("[DashboardService] Datos obtenidos exitosamente");
    console.log("[DashboardService] Proyectos encontrados:", projects.length);
    console.log("[DashboardService] UserData extraído:", userData);

    return {
      userData,
      projects,
    };
  } catch (error) {
    console.error("[DashboardService] Error obteniendo datos:", error);
    return {
      userData: {
        id: 1,
        username: "admin",
        email: "admin@kruger.com",
      },
      projects: [],
    };
  }
}
