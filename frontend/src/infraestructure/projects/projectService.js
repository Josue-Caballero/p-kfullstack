import { cookies } from "next/headers";

// Función para obtener el token del servidor
async function getServerToken() {
  const cookieStore = await cookies();
  return cookieStore.get("token")?.value;
}

// Función para hacer requests autenticados desde el servidor
async function authenticatedFetch(url, options = {}) {
  const token = await getServerToken();

  if (!token) {
    throw new Error("No hay token de autenticación");
  }

  const baseUrl = process.env.NEXT_PUBLIC_API_BASE_URL;

  return fetch(`${baseUrl}${url}`, {
    ...options,
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
      ...options.headers,
    },
  });
}

// Listar proyectos del usuario (SSR)
export async function getProjects() {
  console.log("[ProjectService] Obteniendo proyectos...");

  try {
    const token = await getServerToken();
    console.log(
      "[ProjectService] Token obtenido:",
      token ? "✓ PRESENTE" : "✗ AUSENTE"
    );

    const response = await authenticatedFetch("/projects");
    console.log("[ProjectService] Response status:", response.status);

    if (!response.ok) {
      const errorText = await response.text();
      console.error("[ProjectService] Error response:", errorText);
      console.error(
        "[ProjectService] Error obteniendo proyectos:",
        response.status
      );
      return [];
    }

    const projects = await response.json();
    console.log("[ProjectService] Proyectos obtenidos:", projects.length);
    console.log("[ProjectService] Datos de proyectos:", projects);
    return projects;
  } catch (error) {
    console.error("[ProjectService] Error:", error);
    return [];
  }
}

// Crear proyecto (Server Action)
export async function createProject(projectData) {
  console.log("[ProjectService] Creando proyecto:", projectData);

  try {
    const response = await authenticatedFetch("/projects", {
      method: "POST",
      body: JSON.stringify(projectData),
    });

    if (!response.ok) {
      const errorText = await response.text();
      console.error("[ProjectService] Error creando proyecto:", errorText);
      throw new Error("Error al crear proyecto");
    }

    const project = await response.json();
    console.log("[ProjectService] Proyecto creado exitosamente");
    return project;
  } catch (error) {
    console.error("[ProjectService] Error:", error);
    throw error;
  }
}

// Editar proyecto (Server Action)
export async function updateProject(projectId, projectData) {
  console.log("[ProjectService] Editando proyecto:", projectId, projectData);

  try {
    const response = await authenticatedFetch(`/projects/${projectId}`, {
      method: "PUT",
      body: JSON.stringify(projectData),
    });

    if (!response.ok) {
      const errorText = await response.text();
      console.error("[ProjectService] Error editando proyecto:", errorText);
      throw new Error("Error al editar proyecto");
    }

    const project = await response.json();
    console.log("[ProjectService] Proyecto editado exitosamente");
    return project;
  } catch (error) {
    console.error("[ProjectService] Error:", error);
    throw error;
  }
}

// Eliminar proyecto (Server Action)
export async function deleteProject(projectId) {
  console.log("[ProjectService] *** FUNCIÓN DELETEPROJECT LLAMADA ***");
  console.log("[ProjectService] Eliminando proyecto:", projectId);
  console.log("[ProjectService] Tipo de projectId:", typeof projectId);

  try {
    const token = await getServerToken();
    console.log(
      "[ProjectService] Token para delete:",
      token ? "✓ PRESENTE" : "✗ AUSENTE"
    );

    const url = `/projects/${projectId}`;
    console.log(
      "[ProjectService] URL delete:",
      `${process.env.NEXT_PUBLIC_API_BASE_URL}${url}`
    );

    const response = await authenticatedFetch(url, {
      method: "DELETE",
    });

    console.log("[ProjectService] Delete response status:", response.status);

    if (!response.ok) {
      const errorText = await response.text();
      console.error("[ProjectService] Error eliminando proyecto:", errorText);
      throw new Error(
        `Error al eliminar proyecto: ${response.status} - ${errorText}`
      );
    }

    const deletedProject = await response.json();
    console.log(
      "[ProjectService] Proyecto eliminado exitosamente:",
      deletedProject
    );
    return deletedProject;
  } catch (error) {
    console.error("[ProjectService] Error en deleteProject:", error);
    throw error;
  }
}
