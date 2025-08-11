/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nutrimind_proyecto2_rosibellp.dominio;

/**
 *
 * @author carri
 */
public class Recomendaciones {
    public static String porIMC(String categoria) {
        switch (categoria) {
            case "Bajo peso":
                return """
                        Objetivo: Aumentar peso saludable.
                        • Carbohidratos complejos (arroz integral, avena, papa).
                        • Grasas saludables (aguacate, nueces, aceite de oliva).
                        • 5 comidas al día con refrigerios.
                        • Proteínas de calidad (huevo, pollo, pescado, legumbres).
                        • Evitar saltarse comidas.
                        """;
            case "Normal":
                return """
                        Objetivo: Mantener estado óptimo.
                        • Dieta balanceada: 50% CHO, 20% proteína, 30% grasas saludables.
                        • ≥5 porciones de frutas/vegetales.
                        • ≥8 vasos de agua.
                        • Control de porciones y azúcares simples.
                        • Actividad física regular.
                        """;
            case "Sobrepeso":
                return """
                        Objetivo: Reducir peso progresivamente.
                        • Menos harinas refinadas y azúcares procesados.
                        • Limitar frituras, refrescos y comida rápida.
                        • Aumentar fibra (avena, vegetales crudos, fruta con cáscara).
                        • ≥30 min de actividad física diaria.
                        • Porciones pequeñas 4–5 veces al día.
                        """;
            case "Obesidad I":
                return """
                        Objetivo: Reducir peso y prevenir complicaciones.
                        • Cocción al vapor, horno o hervidos.
                        • Eliminar bebidas azucaradas y jugos procesados.
                        • Registrar comidas y horarios.
                        • Evitar cenas pesadas o tardías.
                        • Consultar nutricionista si es posible.
                        """;
            case "Obesidad II":
            case "Obesidad III":
                return """
                        Objetivo: Reducción controlada con acompañamiento.
                        • Acompañamiento profesional (nutricionista/médico).
                        • Reducción calórica gradual y controlada.
                        • 5 tiempos de comida con énfasis en vegetales.
                        • Actividad física ligera progresiva (caminar, bici estática).
                        • Evaluar comorbilidades (HTA, diabetes, etc.).
                        """;
            default:
                return "";
        }
    }

    public static String porActividad(String actividad) {
        switch (actividad) {
            case "Sedentario":
                return """
                        • Evitar picoteo entre comidas.
                        • Pausas activas si permanece mucho tiempo sentado.
                        • Caminatas de 20 min diarios.
                        """;
            case "Moderado":
            case "Moderadamente activo":
                return """
                        • Snacks saludables pre y post (fruta, yogur, semillas).
                        • Hidratación constante.
                        • Ajustar calorías según gasto energético.
                        """;
            case "Muy activo":
                return """
                        • Suficientes carbohidratos complejos y proteínas.
                        • Reponer electrolitos en actividad intensa.
                        • Más alimentos ricos en hierro y calcio.
                        """;
            default:
                return "";
        }
    }

    public static String combinar(String categoriaIMC, String actividad) {
        return porIMC(categoriaIMC) + "\n" + porActividad(actividad);
    }
    
}
