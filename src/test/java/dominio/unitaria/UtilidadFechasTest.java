package dominio.unitaria;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;

import org.junit.Test;

import dominio.UtilidadFechas;

public class UtilidadFechasTest {
	
	public static int CERO_DIAS = 0;
	public static int UN_DIA = 1;
	public static int CANTIDAD_DIAS_SIN_DOMINGOS = 3;
	public static int QUINCE_DIAS = 15;

	@Test
	public void calcularFechaMaximaDadaSeisDias() {
		
		//arrange
		LocalDate date = LocalDate.of(2018, Month.APRIL, 2);//lunes 4 de abril
		LocalDate dateMas3Dias = LocalDate.of(2018, Month.APRIL, 4);//miercoles 6 de abril
		
		//act
		LocalDate resultado = UtilidadFechas.agregarDias(date, CANTIDAD_DIAS_SIN_DOMINGOS);
		
		//assert
		assertEquals(dateMas3Dias, resultado);
	}
	
	@Test
	public void calcularFechaMaximaSinIncluirDomingos() {
		
		//arrange
		LocalDate date = LocalDate.of(2017, Month.MAY, 24);//miercoles 24 de mayo
		LocalDate dateMas15Dias = LocalDate.of(2017, Month.JUNE, 9);//viernes 9 de junio
		
		//act
		LocalDate resultado = UtilidadFechas.agregarDias(date, QUINCE_DIAS);
		
		//assert
		assertEquals(dateMas15Dias, resultado);
	}
	
	@Test
	public void calcularFechaMaximaDadoUnDia() {
		//arrange
		LocalDate date = LocalDate.of(2018, Month.APRIL, 2);//lunes 4 de abril
		LocalDate dateMas1Dias = LocalDate.of(2018, Month.APRIL, 2);//lunes 4 de abril
		
		//act
		LocalDate resultado = UtilidadFechas.agregarDias(date, UN_DIA);
		
		//assert
		assertEquals(dateMas1Dias, resultado);
	}
	
	@Test
	public void calcularFechaMaximaCeroDia() {
		//arrange
		LocalDate date = LocalDate.of(2018, Month.APRIL, 2);//lunes 4 de abril
		LocalDate dateMas0Dias = LocalDate.of(2018, Month.APRIL, 2);//lunes 4 de abril
		
		//act
		LocalDate resultado = UtilidadFechas.agregarDias(date, CERO_DIAS);
		
		//assert
		assertEquals(dateMas0Dias, resultado);
	}
	
	@Test
	public void convertirLocalDateToDate() throws ParseException {
		//arrange
		LocalDate date = LocalDate.of(2018, Month.APRIL, 2);//lunes 4 de abril
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date lunes4 = sdf.parse("2018/04/02");
		
		//act
		Date resultado = UtilidadFechas.convertirToDate(date);
		
		//assert
		assertEquals(lunes4.getTime(), resultado.getTime());
	}
	
}
