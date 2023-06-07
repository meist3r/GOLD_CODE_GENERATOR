import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*int[] data = {-1, -1, 7, 7, -9, 7, -9, -1, -1, -1, 7, 7, -1, 7, -9, -9, -1, 7, -1, -9, -1, -1, -1, -1, -9, -1, -1, 7, -1, 7, 7};
        LineChart.generateLineChart(data, "Autokorelacja krzyżowa");*/
        Scanner scanner = new Scanner(System.in);

        int choice = 1;
        while(true){
            choice = mainMenu();
            int result[][];
            switch(choice){
                case 1:
                    result = definedMenu();
                    if(result==null){
                        continue;
                    }
                    actionMenu(result);
                    break;
                case 2:
                    result = undefinedMenu();
                    if(result==null){
                        continue;
                    }
                    actionMenu(result);
                    break;
                default:
                    System.out.println("Nieprawidlowy wybor");
                    break;
            }

        }
    }

    public static int mainMenu(){
        Scanner scanner = new Scanner(System.in);
        int choice;
        System.out.println("Generator kodow Golda by Artur Grochal & Michal Wysocki");
        System.out.println("MENU:");
        System.out.println("1. Wybierz predefiniowana pare wielomianow generujacych");
        System.out.println("2. Podaj wlasna pare wielomianow generujacych");
        System.out.print("\n> ");
        choice = scanner.nextInt();
        return choice;
    }

    public static void actionMenu(int [][] data){
        Scanner scanner = new Scanner(System.in);
        int choice;
        Generator g = new Generator(data[0],data[1],data[2],data[3]);
        Validator validator = new Validator(g);
        while(true){
        System.out.println("1. Pokaz wykres korelacji krzyzowej");
        System.out.println("2. Pokaz wykres autokorelacji");
        System.out.println("3. Generuj kolejne kody Golda");
        System.out.print("\n> ");
        choice = scanner.nextInt();
        switch(choice){
            case 1:
                int[] mSeqCorArr = validator.getMSequencesCorrelation();
                LineChart.generateLineChart(mSeqCorArr, "Korelacja krzyżowa");
                break;
            case 2:
                int[] mSeqAutoCorArr = validator.getAutoCorrelation();
                LineChart.generateLineChart(mSeqAutoCorArr, "Autokorelacja");
                break;
            case 3:
                int goldCode = g.generate();
                System.out.println("# " + goldCode);
                // TODO: JAKIS LOOP
                break;
            default:
                break;
        }
        }
    }

    public static int[][] definedMenu(){
        int[][] result = new int[4][];
        Scanner scanner = new Scanner(System.in);
        int pairNum;
        System.out.println("Mozliwe predefiniowane wielomiany generujace:");
        System.out.println("1. x^5 + x^4 + x^3 + x^2 oraz x^5 + x^2   | Dlugosc kodow Golda = 31    | Dlugosc LFSR = 5");
        System.out.println("2. x^6 + x^5 + x^2 + x oraz x^6 + x       | Dlugosc kodow Golda = 63    | Dlugosc LFSR = 6");
        System.out.println("3. x^7 + x^3 + x^2 + x oraz x^7 + x^3     | Dlugosc kodow Golda = 127   | Dlugosc LFSR = 7");
        System.out.println("4. x^7 + x^3 + x^1 oraz x^7 + x           | Dlugosc kodow Golda = 127   | Dlugosc LFSR = 7");
        System.out.println("5. x^9 + x^6 + x^4 + x^3 oraz x^9 + x^4   | Dlugosc kodow Golda = 511   | Dlugosc LFSR = 9");
        System.out.println("6. x^11 + x^8 + x^5 + x^2 oraz x^11 + x^2 | Dlugosc kodow Golda = 2047  | Dlugosc LFSR = 11");
        System.out.print("\n> ");
        pairNum = scanner.nextInt();

        if(pairNum<7 && pairNum>0) {
            int[] pair1 = {};
            int[] pair2 = {};
            switch (pairNum) {
                case 1:
                    pair1 = new int[]{2,3,4,5};
                    pair2 = new int[]{2,5};
                    break;
                case 2:
                    pair1 = new int[]{1,2,5,6};
                    pair2 = new int[]{1,6};
                    break;
                case 3:
                    pair1 = new int[]{1,2,3,7};
                    pair2 = new int[]{3,7};
                    break;
                case 4:
                    pair1 = new int[]{1,3,7};
                    pair2 = new int[]{1,7};
                    break;
                case 5:
                    pair1 = new int[]{3,4,6,9};
                    pair2 = new int[]{4,9};
                    break;
                case 6:
                    pair1 = new int[]{2,5,8,11};
                    pair2 = new int[]{2,11};
                    break;
                default:
                    break;
            }
            System.out.println();

            int[][]seeds = getSeeds();

            int maxMSeq1 = Arrays.stream(pair1).max().getAsInt();
            int maxMSeq2 = Arrays.stream(pair2).max().getAsInt();

            int seedLimit = (maxMSeq1 > maxMSeq2) ? maxMSeq1 : maxMSeq2;

            if(seeds[0].length!=seedLimit || seeds[1].length !=seedLimit){
                System.out.println("Bledna dlugosc ziaren");
                return null;
            }

            result[0] = pair1;
            result[1] = pair2;
            result[2] = seeds[0];
            result[3] = seeds[1];

            return result;

        } else {
            System.out.println("Wybor poza zakresem");
            return null;
        }

    }

    public static int[][] undefinedMenu(){
        int[][] result = new int[4][];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj ilosc skladnikow pierwszego wielomianu");
        System.out.print("\n> ");
        int mSeqLen1 = scanner.nextInt();
        System.out.println("Podaj pierwszy wielomian generujacy, wypisujac wykladniki w nim wystepujace np. 5 4 3 2:");
        System.out.print("\n> ");
        int[] mSeq1 = new int[mSeqLen1];
        for(int i=0;i<mSeqLen1;i++){
            mSeq1[i] = scanner.nextInt();
        }
        System.out.println();
        System.out.println("Podaj ilosc skladnikow drugiego wielomianu");
        System.out.print("\n> ");
        int mSeqLen2 = scanner.nextInt();
        System.out.println("Podaj drugi wielomian generujacy w tym samym formacie:");
        System.out.print("\n> ");
        int[] mSeq2 = new int[mSeqLen2];
        for(int i=0;i<mSeqLen2;i++){
            mSeq2[i] = scanner.nextInt();
        }

        Arrays.sort(mSeq1);
        Arrays.sort(mSeq2);
        mSeq1 = reverseArray(mSeq1);
        mSeq2 = reverseArray(mSeq2);

        int[][]seeds = getSeeds();

        int maxMSeq1 = Arrays.stream(mSeq1).max().getAsInt();
        int maxMSeq2 = Arrays.stream(mSeq2).max().getAsInt();

        int seedLimit = (maxMSeq1 > maxMSeq2) ? maxMSeq1 : maxMSeq2;

        if(seeds[0].length!=seedLimit || seeds[1].length !=seedLimit){
            System.out.println("Bledna dlugosc ziaren");
            return null;
        }

        result[0] = mSeq1;
        result[1] = mSeq2;
        result[2] = seeds[0];
        result[3] = seeds[1];

        return result;

    }

    public static int[][] getSeeds(){ //TODO: Dodaj tutaj informacje jak dlugie to ma byc
        Scanner scanner = new Scanner(System.in);
        int[][] result = new int[2][];
        boolean waitForGoodInput = true;
        int[] seed1 = {};
        int[] seed2 = {};
        while(waitForGoodInput){
            System.out.println("Podaj pierwsza wartosc ziarna w formacie Big Endian (najstarszy bit po lewej) np. 0001:");
            System.out.print("\n> ");
            String seedInput = scanner.next();
            seed1 = extractSeed(seedInput);
            if(seed1==null){
                System.out.println("Bledny format rejestru");
                continue;
            }
            System.out.println();
            System.out.println("Podaj druga wartosc ziarna w tym samym formacie:");
            System.out.print("\n> ");
            seedInput = scanner.next();
            seed2 = extractSeed(seedInput);
            if(seed2==null){
                System.out.println("Bledny format rejestru");
                continue;
            }
            System.out.println();
            waitForGoodInput = false;
        }
        result[0] = seed1;
        result[1] = seed2;

        return result;
    }

    public static int[] extractSeed(String seed){
        int[] result = new int[seed.length()];
        for (int i=0;i<seed.length();i++) {
            char c = seed.charAt(i);
            if(c=='0'){
                result[i] = 0;
            } else if(c=='1'){
                result[i] = 1;
            } else {
                return null;
            }
        }
        return result;
    }

    public static int[] reverseArray(int[] array) {
        int[] reversedArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversedArray[i] = array[array.length - 1 - i];
        }
        return reversedArray;
    }


}
