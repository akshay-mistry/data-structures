public class SuperListRunner {

    public SuperListRunner() {

        SuperList<Integer> superList = new SuperList<>();
        for (int i = 0; i < 30; i++) {
            superList.add((int)(Math.random()*1000)+1);
        }
        System.out.println("SuperList: " + superList);
        int size = superList.size();
        System.out.println("Size: " + superList.size());

        SuperList<Integer> stackSuperList = new SuperList<>();
        for (int i = 0; i < size; i++) {
            stackSuperList.push(superList.remove(0));
        }
        System.out.println("Stack Version: " + stackSuperList);

        SuperList<Integer> queueSuperList = new SuperList<>();
        for (int i = 0; i < size; i++) {
            queueSuperList.add(stackSuperList.pop());
        }
        System.out.println("Queue Version: " + queueSuperList);

        for (int i = 0; i < size; i++) {
            superList.add((int)(Math.random()* superList.size()),queueSuperList.poll());
        }
        System.out.println("ArrayList Version: " + superList);

        int sum = 0;
        int sumEven = 0;
        int sumOdd = 0;
        for (int i = 0; i < size; i++) {
            sum += superList.get(i);
            if (i % 2 == 0) {
                sumEven += superList.get(i);
            }
            else {
                sumOdd += superList.get(i);
            }
        }
        System.out.println("Sum: " + sum);
        System.out.println("Sum of even indexed values: " + sumEven);
        System.out.println("Sum of odd indexed values: " + sumOdd);

        for (int i = 0; i < size; i++) {
            if (i % 2 == 0) {
                int value = superList.get(i);
                superList.add(value);
            }
        }
        System.out.println("List with even duplicates: " + superList);

        for (int i = 0; i < size; i++) {
            if (superList.get(i) != null && superList.get(i) % 3 == 0) {
                superList.remove(i);
            }
        }
        System.out.println("List without multiples of 3: " + superList);

        superList.add(4, 55555);
        System.out.println("List with added value: " + superList);

        for (int i = 0; i < superList.size(); i++) {
            for (int j = i+1; j < superList.size(); j++) {
                if(superList.get(i) < superList.get(j)) {
                    int iValue = superList.get(i);
                    int jValue = superList.get(j);
                    superList.remove(j);
                    superList.remove(i);
                    superList.add(i, jValue);
                    superList.add(j, iValue);
                }
            }
        }
        System.out.println("List in descending order: " + superList);

        double median = 0;
        int medianIndex = size/2;
        SuperList<Integer> valuesBeforeMedian = new SuperList<>();
        SuperList<Integer> valuesAfterMedian = new SuperList<>();
        System.out.println("Median Index: " + medianIndex);

        if (size % 2 == 0) {
            median = 0.5 * (superList.get((size/2)-1) + superList.get(size/2));
            for (int i = 0; i < medianIndex-1; i++) {
                valuesBeforeMedian.add(superList.get(i));
            }
            for (int i = medianIndex + 1; i < size; i++) {
                valuesAfterMedian.add(superList.get(i));
            }
        }
        else {
            for (int i = 0; i < medianIndex; i++) {
                valuesBeforeMedian.add(superList.get(i));
            }
            for (int i = medianIndex + 1; i < size; i++) {
                valuesAfterMedian.add(superList.get(i));
            }
            median = superList.get(medianIndex);
        }
        System.out.println("Median: " + median);
        System.out.println("Values before median: " + valuesBeforeMedian);
        System.out.println("Values after median: " + valuesAfterMedian);

        SuperList<String> sentenceList = new SuperList<>();
        String sentence = "The quick brown fox jumps over the lazy dog";
        String[] arr = sentence.split(" ");
        for (String s : arr) {
            sentenceList.add(s);
        }
        System.out.println("List of words: " + sentenceList);

        for (int i = 0; i < sentenceList.size(); i++) {
            String word = sentenceList.get(i);
            if (word.length() <= 3) {
                sentenceList.remove(i);
            }
        }
        System.out.println("List without short words: " + sentenceList);

        for (int i = 1; i < sentenceList.size(); i++) {
            int index = i-1;
            while (index >= 0 && sentenceList.get(index).compareTo(sentenceList.get(index+1)) > 0) {
                String str = sentenceList.get(index);
                String str2 = sentenceList.get(index+1);
                sentenceList.remove(index+1);
                sentenceList.add(index+1, str);
                sentenceList.remove(index);
                sentenceList.add(index, str2);
                index--;
            }
        }
        System.out.println("List in ascending order: " + sentenceList);

        int characterTotal = 0;
        int words = sentenceList.size();
        for (int i = 0; i < words; i++) {
            characterTotal += sentenceList.get(i).length();
        }
        double averageLength = (double)characterTotal/words;
        System.out.println("Average word length: " + averageLength);
    }

    public static void main (String[] args) {
        SuperListRunner superListRunner = new SuperListRunner();
    }
}
