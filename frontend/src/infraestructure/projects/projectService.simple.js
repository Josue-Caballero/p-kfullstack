// Versión simplificada para testing
const API_BASE_URL =
  process.env.API_BASE_URL || "http://localhost:8080/kdevfull";

export async function getProjects() {
  console.log("[ProjectService] Obteniendo proyectos...");

  try {
    // Simulamos una respuesta por ahora
    return [
      {
        id: 1,
        name: "Proyecto de Prueba",
        description: "Este es un proyecto de prueba",
        owner: 1,
        createdAt: new Date().toISOString(),
      },
    ];
  } catch (error) {
    console.error("[ProjectService] Error:", error);
    return [];
  }
}

export async function createProject(projectData) {
  console.log("[ProjectService] Creando proyecto:", projectData);
  return { id: Date.now(), ...projectData };
}

export async function updateProject(projectId, projectData) {
  console.log(
    "[ProjectService] Actualizando proyecto:",
    projectId,
    projectData
  );
  return { id: projectId, ...projectData };
}

export async function deleteProject(projectId) {
  console.log("[ProjectService] Eliminando proyecto:", projectId);
  return true;
}
