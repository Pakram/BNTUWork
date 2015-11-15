import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	List<String> input = Files.readAllLines(Paths.get("src/input"), Charset.forName("UTF-8"));
	for (String line : input) {
	    System.out.println(line);
	}
	Scanner in = new Scanner(System.in);
	Service service = new Service();
	int variant = in.nextInt();
	switch (variant) {
	    case 1:
		service.calculateFirstVariant();
		break;
	    case 2:
		service.calculateSecondVariant();
		break;
	    case 3:
		service.calculateThirdVariant();
		break;
	    case 4:
		service.calculateFourthVariant();
		break;
	    case 5:
		service.calculateFirstVariant();
		service.calculateSecondVariant();
		service.calculateThirdVariant();
		service.calculateFourthVariant();
		break;
	    default:
		System.out.println("Введите число жителей");
		int numberOfPeoples = in.nextInt();
		System.out.println("Введите пробег автомобилей");
		int Lg = in.nextInt();
		System.out.println("Введите количество автомобилей на 1000 жителей");
		int cntAuto = in.nextInt();
		System.out.println("Введите класс автомобилей");
		int classAuto = in.nextInt();
		service.manualVariant(numberOfPeoples, Lg, cntAuto, classAuto);
		break;
	}
    }
}
