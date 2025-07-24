import { cookies } from "next/headers";
import { redirect } from "next/navigation";
import LoginForm from "@/ui/components/LoginForm";

export default async function LoginPage() {
  const cookieStore = await cookies(); // ✅ Usa await
  const token = cookieStore.get("token")?.value;

  if (token) {
    redirect("/dashboard");
  }

  return <LoginForm />;
}
