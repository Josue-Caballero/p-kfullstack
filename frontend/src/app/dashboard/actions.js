"use server";

import {
  createProject,
  updateProject,
  deleteProject,
} from "@/infraestructure/projects/projectService";
import { revalidatePath } from "next/cache";

export async function handleCreateProject(formData) {
  console.log("[ProjectActions] Creando proyecto...");

  try {
    const projectData = {
      name: formData.get("name"),
      description: formData.get("description"),
      owner: parseInt(formData.get("ownerId")), // El ID del usuario logueado
    };

    const project = await createProject(projectData);

    // Revalidar la página para mostrar el nuevo proyecto
    revalidatePath("/dashboard");

    return { success: true, project };
  } catch (error) {
    console.error("[ProjectActions] Error creando proyecto:", error);
    return { success: false, error: error.message };
  }
}

export async function handleUpdateProject(projectId, formData) {
  console.log("[ProjectActions] Editando proyecto:", projectId);

  try {
    const projectData = {
      name: formData.get("name"),
      description: formData.get("description"),
    };

    const project = await updateProject(projectId, projectData);

    // Revalidar la página para mostrar los cambios
    revalidatePath("/dashboard");

    return { success: true, project };
  } catch (error) {
    console.error("[ProjectActions] Error editando proyecto:", error);
    return { success: false, error: error.message };
  }
}

export async function handleDeleteProject(projectId) {
  console.log("[ProjectActions] *** INICIANDO handleDeleteProject ***");
  console.log("[ProjectActions] projectId recibido:", projectId);
  console.log("[ProjectActions] Tipo de projectId:", typeof projectId);

  try {
    console.log("[ProjectActions] Llamando a deleteProject...");
    const deletedProject = await deleteProject(projectId);
    console.log(
      "[ProjectActions] Proyecto eliminado desde servicio:",
      deletedProject
    );

    // Revalidar la página para mostrar los cambios
    revalidatePath("/dashboard");

    return { success: true, project: deletedProject };
  } catch (error) {
    console.error("[ProjectActions] Error eliminando proyecto:", error);
    return { success: false, error: error.message };
  }
}
