package bibliotecafusm;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Paulker
 */
public class Calendario implements Serializable {



    public static int diaSemanaFecha(Date base) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(base);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        return day;
    }

    /**
     * se calcula la fecha del dia siguiente habilitado para el cobro no se
     * tienen en cuenta ni Domingos ni Dias Festivos
     *
     * @param base se recive una fecha base para el calcular el dia siguiente
     * @return retorna el dia siguiente teniendo en cuenta las restricciones
     *
     */
    public static Date getcalcFechaDevolucion(Date base, int days) {
        Date nextDay = sumarDiasFecha(base, days);
        int day = diaSemanaFecha(nextDay);
        boolean festivo = true;

        while (festivo) {
            if (day == 1) {
                //esto quiere decir que es un domingo asi que se salta ese dia 
                //se debe de sumar un dia mas 
                nextDay = sumarDiasFecha(nextDay, 1);
                day = diaSemanaFecha(nextDay);
            } else if (day == 7) {
                nextDay = sumarDiasFecha(nextDay, 1);
                day = diaSemanaFecha(nextDay);
            }

            festivo = false;

        }

        return nextDay;
    }

    /**
     * para restar el valor de las cuotas debe ser negativo
     *
     * @param inicio fecha base a la cual se le suma o resta dias
     * @param dias numero de dias que se desea sumar o restar
     * @return Date fecha con los dias calculados
     */
    public static Date sumarDiasFecha(Date inicio, int dias) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inicio);
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        return calendar.getTime();
    }

    private static boolean isFestivo(Date fecha) {
        boolean festivo = false;
        //aqui se consulta si la fecha dada es un dia festivo
        //Se busca dentro la BD de dias festivos si se encuentra
        return festivo;

    }
    
  
}
