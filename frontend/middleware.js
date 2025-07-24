// middleware.js
import { NextResponse } from "next/server";

export function middleware(request) {
  const token = request.cookies.get("token")?.value;
  const pathname = request.nextUrl.pathname;
  const isLoginPage = pathname.startsWith("/login");
  const isRootPage = pathname === "/";
  const isApiRoute = pathname.startsWith("/api");

  console.log("[Middleware] Ruta:", pathname);
  console.log("[Middleware] Token:", token ? "PRESENTE" : "AUSENTE");

  // No aplicar middleware a rutas de API
  if (isApiRoute) {
    return NextResponse.next();
  }

  // Si está en la raíz
  if (isRootPage) {
    if (token) {
      console.log("[Middleware] Redirigiendo de raíz a dashboard");
      return NextResponse.redirect(new URL("/dashboard", request.url));
    } else {
      console.log("[Middleware] Redirigiendo de raíz a login");
      return NextResponse.redirect(new URL("/login", request.url));
    }
  }

  // Si no hay token y no está en login, redirigir a login
  if (!token && !isLoginPage) {
    console.log("[Middleware] Sin token, redirigiendo a login");
    return NextResponse.redirect(new URL("/login", request.url));
  }

  // Si hay token y está en login, redirigir a dashboard
  if (token && isLoginPage) {
    console.log("[Middleware] Con token en login, redirigiendo a dashboard");
    return NextResponse.redirect(new URL("/dashboard", request.url));
  }

  console.log("[Middleware] Permitiendo acceso");
  return NextResponse.next();
}

export const config = {
  matcher: [
    /*
     * Match all request paths except for the ones starting with:
     * - api (API routes)
     * - _next/static (static files)
     * - _next/image (image optimization files)
     * - favicon.ico (favicon file)
     */
    "/((?!api|_next/static|_next/image|favicon.ico).*)",
  ],
};
