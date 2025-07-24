"use client";

import { useState } from "react";
import { handleDeleteProject } from "@/app/dashboard/actions";

export default function ProjectCard({ project, onEdit }) {
  const [isDeleting, setIsDeleting] = useState(false);

  const handleDelete = async () => {
    if (!confirm("¿Estás seguro de que quieres eliminar este proyecto?")) {
      return;
    }

    setIsDeleting(true);
    try {
      const result = await handleDeleteProject(project.id);

      if (result.success) {
        window.location.reload(); // Recargar para mostrar cambios
      } else {
        alert(
          "Error al eliminar el proyecto: " +
            (result.error || "Error desconocido")
        );
      }
    } catch (error) {
      console.error("Error eliminando proyecto:", error);
      alert("Error al eliminar el proyecto: " + error.message);
    } finally {
      setIsDeleting(false);
    }
  };

  return (
    <div className="bg-white rounded-lg shadow-md hover:shadow-lg transition-shadow duration-200 p-6 border border-gray-200">
      {/* Header del proyecto */}
      <div className="flex justify-between items-start mb-4">
        <div className="flex-1">
          <h3 className="text-xl font-semibold text-gray-800 mb-2">
            {project.name}
          </h3>
          <p className="text-gray-600 text-sm mb-3">
            {project.description || "Sin descripción"}
          </p>
        </div>

        {/* Acciones */}
        <div className="flex space-x-2 ml-4">
          <button
            onClick={() => onEdit(project)}
            className="p-2 text-blue-600 hover:bg-blue-50 rounded-full transition-colors"
            title="Editar proyecto"
          >
            <svg
              className="w-5 h-5"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
            >
              <path
                strokeLinecap="round"
                strokeLinejoin="round"
                strokeWidth="2"
                d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"
              />
            </svg>
          </button>

          <button
            onClick={handleDelete}
            disabled={isDeleting}
            className="p-2 text-red-600 hover:bg-red-50 rounded-full transition-colors disabled:opacity-50"
            title="Eliminar proyecto"
          >
            {isDeleting ? (
              <div className="w-5 h-5 border-2 border-red-600 border-t-transparent rounded-full animate-spin"></div>
            ) : (
              <svg
                className="w-5 h-5"
                fill="none"
                stroke="currentColor"
                viewBox="0 0 24 24"
              >
                <path
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16"
                />
              </svg>
            )}
          </button>
        </div>
      </div>

      {/* Información adicional */}
      <div className="border-t pt-4">
        <div className="flex justify-between items-center text-sm">
          <div className="flex items-center space-x-4">
            <span className="text-gray-500">
              Propietario:{" "}
              <span className="font-medium text-gray-700">
                {project.owner?.username || "N/A"}
              </span>
            </span>
            <span className="text-gray-500">
              Tareas:{" "}
              <span className="font-medium text-gray-700">
                {project.tasks?.length || 0}
              </span>
            </span>
          </div>
          <span className="text-gray-400">
            {new Date(project.createdAt).toLocaleDateString()}
          </span>
        </div>
      </div>

      {/* Badge de estado */}
      <div className="mt-3">
        <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-green-100 text-green-800">
          Activo
        </span>
      </div>
    </div>
  );
}
