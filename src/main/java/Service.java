import org.knowm.xchart.Chart;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.logging.Logger;
/**
 * Created by Pokramovich on 10.11.2015.
 */
public class Service {
    private final static double Kz = 1.0;//коэф.учитывающий климатическую зону
    private final static int AUTO_PER_POST = 200;//Количество автомобилей на 1 пост
    private final static double Kkp = 0.75;//коэф.учитывающий число клиентов
    private final static int TIME_FOND = 1724;//фонд рабочего времени
    private static final Logger log = Logger.getLogger("Service");
    public void calculateFirstVariant() {
	int[] numberOfPeople = new int[]{20000, 30000, 40000};
	int Lg = 30000;
	int cntAut = 300;
	int classAuto = 3;
	double[] xData = new double[numberOfPeople.length];
	double[] yData = new double[numberOfPeople.length];
	for (int i = 0; i < numberOfPeople.length; i++) {
	    int Acto = (int) Math.round(Math.pow(10, -3) * numberOfPeople[i] * cntAut * Kkp);
	    double Tto = Acto * Lg * getTrudoemkost(classAuto, cntAut) * Math.pow(10, -3);
	    double cntWorkers = Tto / TIME_FOND;
	    printInformation(numberOfPeople[i], Lg, cntAut, classAuto, Acto, Tto, cntWorkers, 1);
	    xData[i] = numberOfPeople[i];
	    yData[i] = (int) Math.round(cntWorkers);
	}
	Chart chart = QuickChart.getChart("Расчет количества рабочих", "Кол. населения в регионе", "Кол.рабочих", "Кол.рабочих от населения", xData, yData);
	new SwingWrapper(chart).displayChart();
    }

    public void calculateSecondVariant() {
	int numberOfPeople = 35000;
	int[] Lg = new int[]{20000, 30000, 40000};
	int cntAut = 250;
	int classAuto = 3;
	double[] xData = new double[Lg.length];
	double[] yData = new double[Lg.length];
	for (int i = 0; i < Lg.length; i++) {
	    int Acto = (int) (Math.pow(10, -3) * numberOfPeople * cntAut * Kkp);
	    double Tto = Acto * Lg[i] * getTrudoemkost(classAuto, cntAut) * Math.pow(10, -3);
	    double cntWorkers = Tto / TIME_FOND;
	    printInformation(numberOfPeople, Lg[i], cntAut, classAuto, Acto, Tto, cntWorkers, 2);
	    xData[i] = Lg[i];
	    yData[i] = (int) Math.round(cntWorkers);
	}
	Chart chart = QuickChart.getChart("Расчет количества рабочих", "Пробег авто", "Кол.рабочих", "Кол.рабочих от пробега авто", xData, yData);
	new SwingWrapper(chart).displayChart();
    }

    public void calculateThirdVariant() {
	int numberOfPeople = 20000;
	int Lg = 25000;
	int[] cntAut = new int[]{150, 250, 350};
	int classAuto = 3;
	double[] xData = new double[cntAut.length];
	double[] yData = new double[cntAut.length];
	for (int i = 0; i < cntAut.length; i++) {
	    int Acto = (int) (Math.pow(10, -3) * numberOfPeople * cntAut[i] * Kkp);
	    double Tto = Acto * Lg * getTrudoemkost(classAuto, cntAut[i]) * Math.pow(10, -3);
	    double cntWorkers = Tto / TIME_FOND;
	    printInformation(numberOfPeople, Lg, cntAut[i], classAuto, Acto, Tto, cntWorkers, 3);
	    xData[i] = cntAut[i];
	    yData[i] = (int) Math.round(cntWorkers);
	}
	Chart chart = QuickChart.getChart("Расчет количества рабочих", "Кол. автомобилей на 1000чел.нас", "Кол.рабочих", "Кол.рабочих от кол.автомобилей на 1000чел.нас", xData, yData);
	new SwingWrapper(chart).displayChart();
    }

    public void calculateFourthVariant() {
	int numberOfPeople = 25000;
	int Lg = 25000;
	int cntAut = 250;
	int[] classAuto = new int[]{1, 2, 3};
	double[] xData = new double[classAuto.length];
	double[] yData = new double[classAuto.length];
	for (int i = 0; i < classAuto.length; i++) {
	    int Acto = (int) (Math.pow(10, -3) * numberOfPeople * cntAut * Kkp);
	    double Tto = Acto * Lg * getTrudoemkost(classAuto[i], cntAut) * Math.pow(10, -3);
	    double cntWorkers = Tto / TIME_FOND;
	    printInformation(numberOfPeople, Lg, cntAut, classAuto[i], Acto, Tto, cntWorkers, 4);
	    xData[i] = classAuto[i];
	    yData[i] = (int) Math.round(cntWorkers);
	}
	Chart chart = QuickChart.getChart("Расчет количества рабочих", "Класс автомобиля", "Кол.рабочих", "Кол.рабочих от класса автомобиля", xData, yData);
	new SwingWrapper(chart).displayChart();
    }

    public void manualVariant(int numberOfPeople, int Lg, int cntAut, int classAuto) {
	int Acto = (int) (Math.pow(10, -3) * numberOfPeople * cntAut * Kkp);
	double Tto = Acto * Lg * getTrudoemkost(classAuto, cntAut) * Math.pow(10, -3);
	double cntWorkers = Tto / TIME_FOND;
	printInformation(numberOfPeople, Lg, cntAut, classAuto, Acto, Tto, cntWorkers, 6);

    }

    private double getTrudoemkost(int classAuto, int cntAuto) {
	double K = getKpost(cntAuto);
	switch (classAuto) {
	    case 1:
		return 2.0 * Kz * K;
	    case 2:
		return 2.3 * Kz * K;
	    case 3:
		return 2.7 * Kz * K;
	    default:
		return 2.7 * Kz * K;
	}
    }

    private double getKpost(int cntAuto) {
	int cntPost = Math.round(cntAuto / AUTO_PER_POST);
	if (cntPost <= 5) {
	    return 1.05;
	} else if (cntPost > 5 && cntPost <= 10) {
	    return 1.0;
	} else if (cntPost > 10 && cntPost <= 15) {
	    return 0.95;
	} else if (cntPost > 15 && cntPost <= 25) {
	    return 0.9;
	} else if (cntPost > 25 && cntPost <= 35) {
	    return 0.85;
	} else {
	    return 0.8;
	}

    }

    private void printInformation(int numberOfPeople, int Lg, int cntAut, int classAuto, int Acto, double Tto, double cntWorkers, int variant) {
	StringBuffer sb = new StringBuffer();
	sb.append("\nКоличество жителей в регионе:").append(numberOfPeople);
	sb.append("\nГодовой пробег автомобилей:").append(Lg);
	sb.append("\nЧисло автомобилей на 1000 чел.:").append(cntAut);
	sb.append("\nКласс автомобиля:").append(classAuto);
	sb.append("\nЧисло комплексно обслуживаемых автомобилей на СТОА:").append(Acto);
	sb.append("\nГодовой объем работ по ТО и ТР:").append(Math.round(Tto));
	sb.append("\nНЕОБХОДИМОЕ ЧИСЛО РАБОЧИХ:").append(Math.round(cntWorkers));
	sb.append("\n----------------------------------------------------------");
	try {
	    PrintWriter writer = new PrintWriter(new File("output" + variant + ".txt"));
	    writer.write(sb.toString());
	    writer.close();
	} catch (FileNotFoundException e) {
	    log.info("Файл не найден");
	    log.warning(e.getLocalizedMessage());
	}
	System.out.println(sb.toString());
    }
}
