public class TimPertandingan {
    public static void main (String[] args) {
         List<Pemain> timA = Arrays.asList(
             new Pemain(168, 50),
             new Pemain(170, 60),
             new Pemain(165, 56),
             new Pemain(168, 55),
             new Pemain(172, 60),
             new Pemain(170, 70),
             new Pemain(169, 66),
             new Pemain(165, 56),
             new Pemain(171, 72),
             new Pemain(166, 56)
         );
    
         List<Pemain> timB = Arrays.asList(
             new Pemain(170, 66),
             new Pemain(167, 60),
             new Pemain(165, 59),
             new Pemain(166, 58),
             new Pemain(168, 58),
             new Pemain(175, 71),
             new Pemain(172, 68),
             new Pemain(171, 68),
             new Pemain(168, 65),
             new Pemain(169, 60)
         );
    
         // a. Urutkan berdasarkan Tinggi Badan (Ascending)
         System.out.println("Urutan berdasarkan Tinggi Badan Ascending (gabungan):");
         List<Pemain> mix = new ArrayList<>();
         mix.addAll(timA);
         mix.addAll(timB);
         mix.sort(Comparator.comparingInt(p -> p.tinggi));
         mix.forEach(System.out::println);
    
         // a. Urutkan berdasarkan Tinggi Badan (Descending)
         System.out.println("\nUrutan berdasarkan Tinggi Badan Descending (gabungan):");
         mix.sort((p1, p2) -> p2.tinggi - p1.tinggi);
         mix.forEach(System.out::println);
    
         // b. Urutkan berdasarkan Berat Badan (Ascending)
         System.out.println("\nUrutan berdasarkan Berat Badan Ascending (gabungan):");
         mix.sort(Comparator.comparingInt(p -> p.berat));
         mix.forEach(System.out::println);
    
         // b. Urutkan berdasarkan Berat Badan (Descending)
.println("\nUrutan berdasarkan Berat Badan Descending (gabungan):");
        mix.sort((p1, p2) -> p2.berat - p1.berat);
        mix.forEach(System.out::println);
    
        // c. Cari nilai max dan min Tinggi dan Berat untuk masing-masing tim
        System.out.println("\nNilai max dan min Tim A:");
        printMaxMin(timA);
    
        System.out.println("\nNilai max dan min Tim B:");
        printMaxMin(timB);
    
        // d. Copy seluruh anggota Tim B ke Tim C yang baru dibentuk
        List<Pemain> timC = new ArrayList<>(timB);
        System.out.println("\nAnggota Tim C (copy dari Tim B):");
        timC.forEach(System.out::println);
    }
    
    private static void printMaxMin(List<Pemain> tim) {
        int maxTinggi = tim.stream().mapToInt(p -> p.tinggi).max().orElse(-1);
        int minTinggi = tim.stream().mapToInt(p -> p.tinggi).min().orElse(-1);
        int maxBerat = tim.stream().mapToInt(p -> p.berat).max().orElse(-1);
        int minBerat = tim.stream().mapToInt(p -> p.berat).min().orElse(-1);
    
        System.out.println("Max Tinggi: " + maxTinggi);
        System.out.println("Min Tinggi: " + minTinggi);
        System.out.println("Max Berat: " + maxBerat);
        System.out.println("Min Berat: " + minBerat);
     
        //----------------------------------------------------------------------------------------------------
     
        ArrayList<Pemain> timA = new ArrayList<>(Arrays.asList(
            new Pemain(168, 50),
            new Pemain(170, 60),
            new Pemain(165, 56),
            new Pemain(168, 55),
            new Pemain(172, 60),
            new Pemain(170, 70),
            new Pemain(169, 66),
            new Pemain(165, 56),
            new Pemain(171, 72),
            new Pemain(166, 56)
        ));
    
        ArrayList<Pemain> timB = new ArrayList<>(Arrays.asList(
            new Pemain(170, 66),
            new Pemain(167, 60),
            new Pemain(165, 59),
            new Pemain(166, 58),
            new Pemain(168, 58),
            new Pemain(175, 71),
            new Pemain(172, 68),
            new Pemain(171, 68),
            new Pemain(168, 65),
            new Pemain(169, 60)
        ));
    
        // Urutkan berdasarkan tinggi badan untuk binary search
        timB.sort(Comparator.comparingInt(p -> p.tinggi));
        timA.sort(Comparator.comparingInt(p -> p.berat));
    
        // b) Cari jumlah pemain di tim B dengan tinggi 168 dan 160
        System.out.println("Jumlah pemain Tim B dengan tinggi 168: " + countByTinggi(timB, 168) + " orang.");
        System.out.println("Jumlah pemain Tim B dengan tinggi 160: " + countByTinggi(timB, 160) + " orang.");
    
        // c) Cari jumlah pemain di tim A dengan berat 56 dan 53
        System.out.println("Jumlah pemain Tim A dengan berat 56: " + countByBerat(timA, 56) + " orang.");
        System.out.println("Jumlah pemain Tim A dengan berat 53: " + countByBerat(timA, 53) + " orang.");
    
        // d) Cek apakah ada pemain Tim A dengan tinggi atau berat sama dengan pemain Tim B
        boolean adaSama = adaTinggiAtauBeratSama(timA, timB);
        System.out.println("Apakah ada pemain Tim A yang memiliki tinggi atau berat sama dengan pemain Tim B? " + (adaSama ? "Ya" : "Tidak"));
    }
    
    // Binary search untuk mencari indeks pertama dari nilai tertentu pada atribut tinggi
    private static int binarySearchTinggi(ArrayList<Pemain> list, int target) {
        int left = 0, right = list.size() - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid).tinggi == target) {
                result = mid;
                right = mid - 1; // cari yang paling kiri
            } else if (list.get(mid).tinggi < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    // Hitung jumlah pemain dengan tinggi tertentu menggunakan binary search
    private static int countByTinggi(ArrayList<Pemain> list, int target) {
        int firstIndex = binarySearchTinggi(list, target);
        if (firstIndex == -1) return 0;
    
        int count = 0;
        for (int i = firstIndex; i < list.size() && list.get(i).tinggi == target; i++) {
            count++;
        }
        return count;
    }
    
    // Binary search untuk berat (mirip dengan tinggi)
    private static int binarySearchBerat(ArrayList<Pemain> list, int target) {
        int left = 0, right = list.size() - 1;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (list.get(mid).berat == target) {
                result = mid;
                right = mid - 1;
            } else if (list.get(mid).berat < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    private static int countByBerat(ArrayList<Pemain> list, int target) {
        int firstIndex = binarySearchBerat(list, target);
        if (firstIndex == -1) return 0;
    
        int count = 0;
        for (int i = firstIndex; i < list.size() && list.get(i).berat == target; i++) {
            count++;
        }
        return count;
    }
    
    // Cek apakah ada pemain di tim A yang memiliki tinggi atau berat sama dengan pemain di tim B
    private static boolean adaTinggiAtauBeratSama(ArrayList<Pemain> timA, ArrayList<Pemain> timB) {
        Set<Integer> tinggiB = new HashSet<>();
        Set<Integer> beratB = new HashSet<>();
    
        for (Pemain p : timB) {
            tinggiB.add(p.tinggi);
            beratB.add(p.berat);
        }
    
        for (Pemain p : timA) {
            if (tinggiB.contains(p.tinggi) || beratB.contains(p.berat)) {
                return true;
            }
        }
        return false;
    }
}

class Pemain {
    int tinggi, berat;

    public Pemain(int tinggi, int berat) {
        this.tinggi = tinggi;
        this.berat = berat;
    }

    @Override
    public String toString() {
        return "Tinggi: " + tinggi + " Berat: " + berat;
    }
}
