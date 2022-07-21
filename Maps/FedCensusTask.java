import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FedCensusTask {

    public FedCensusTask() {
        //Use the text file FedCensus1930_CambriaCountyPA.txt to code the following task.

        try {
            TreeMap<String, TreeSet<Citizen>> citizenMap = new TreeMap<>();
            TreeSet<Citizen> masterSet = new TreeSet<>();

            BufferedReader input = new BufferedReader(new FileReader("/Users/akshaymistry/Desktop/Data Structures/Maps/FedCensus1930_CambriaCountyPA.txt"));
            String text = "";
            while ((text=input.readLine())!=null) {
                if (text.startsWith("17")) {
                    String first=text.substring(71,88).trim();
                    String last=text.substring(55,71).trim();
                    String streetName=text.substring(20,36).trim();
                    String streetNumber=text.substring(36,45).trim();
                    String relation=text.substring(88,108).trim();
                    String rentOwn=text.substring(108,113).trim();
                    String valueProp=text.substring(113,121).trim();
                    String gender=text.substring(133,134).trim();
                    String age=text.substring(143,151).trim();
                    String maritalStatus=text.substring(151,156).trim();
                    String ageFirstMarriage=text.substring(156,162).trim();
                    String attendSchool=text.substring(162,167).trim();
                    String canRead=text.substring(167,173).trim();
                    String birthplace=text.substring(173,190).trim();
                    String fathersBirthplace=text.substring(190,207).trim();
                    String mothersBirthplace=text.substring(207,224).trim();
                    String motherTongue=text.substring(224,235).trim();
                    String yearImmigrated=text.substring(235,241).trim();
                    String occupation=text.substring(252,274).trim();
                    String industry=text.substring(274,303).trim();
                    String transcribedRemarks=text.substring(342).trim();
                    Citizen citizen = new Citizen(first, last, streetName, streetNumber, relation, rentOwn, valueProp, gender, age, maritalStatus, ageFirstMarriage, attendSchool, canRead, birthplace, fathersBirthplace, mothersBirthplace, motherTongue, yearImmigrated, occupation, industry, transcribedRemarks);
                    masterSet.add(citizen);
                    citizenMap.put(citizen.getStreet(), null);
                }
            }


            Iterator<Citizen> it1 = masterSet.iterator();
            while (it1.hasNext()) {
                Citizen c = it1.next();
                String key = c.getStreet();
                TreeSet<Citizen> citizenSet = citizenMap.get(key);
                if (citizenSet == null) {
                    citizenSet = new TreeSet<>();
                    citizenSet.add(c);
                    citizenMap.put(key, citizenSet);
                }
                else {
                    citizenSet.add(c);
                }
            }
            Iterator<Map.Entry<String, TreeSet<Citizen>>> it2 = citizenMap.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<String, TreeSet<Citizen>> value = it2.next();
                System.out.println(value);
            }
            System.out.println("\n");


            //Ages
            TreeMap<Citizen, Double> tempMap = new TreeMap<>();
            Iterator<Citizen> it3 = masterSet.iterator();
            while (it3.hasNext()) {
                Citizen c = it3.next();
                String age = c.getAge();
                if (age.charAt(0)=='0') {
                    age = age.substring(2);
                }
                if (age.equals(".") || age.equals("un")) {
                    age = "0";
                }
                if (age.contains("*")) {
                    age = age.substring(0, age.indexOf("*"));
                }
               if (age.contains("/") && age.contains(" ")) {
                    double whole = Double.valueOf(age.substring(0, age.indexOf(" ")));
                    age = age.substring(2);
                    double numer = Double.valueOf(age.substring(0,age.indexOf("/")));
                    double denom = Double.valueOf(age.substring(age.indexOf("/")+1));
                    double value = whole + (numer/denom);
                    age = String.valueOf(value);
                }
                else if (age.contains("/")) {
                    double numer = Double.valueOf(age.substring(0,age.indexOf("/")));
                    double denom = Double.valueOf(age.substring(age.indexOf("/")+1));
                    double value = numer/denom;
                    age = String.valueOf(value);
                }
                double ageDouble = Double.valueOf(age);
                tempMap.put(c, ageDouble);
            }
            TreeMap<String, PriorityQueue> ageMap = new TreeMap<>();
            Iterator<Map.Entry<Citizen, Double>> it5 = tempMap.entrySet().iterator();
            while (it5.hasNext()) {
                Citizen c = it5.next().getKey();
                double d = it5.next().getValue();
                String key = c.getBirthplace();
                PriorityQueue citizenAges = ageMap.get(key);
                if (citizenAges == null) {
                    citizenAges = new PriorityQueue();
                }
                citizenAges.add(d);
                ageMap.put(key, citizenAges);

            }
            Iterator<Map.Entry<String, PriorityQueue>> it6 = ageMap.entrySet().iterator();
            while (it6.hasNext()) {
                Map.Entry<String, PriorityQueue> value = it6.next();
                if (value.getKey().equals("Pennsylvania")) {
                    System.out.println("Pennsylvania="+value.getValue().size() +" citizens born in Pennsylvania");
                }
                else {
                    System.out.println(value);
                }
            }
            System.out.println("\n");



            //Mother Tongue
            TreeMap<String, ArrayList<String>> motherTongueMap = new TreeMap<>();
            Iterator<Citizen> it7 = masterSet.iterator();
            while (it7.hasNext()) {
                Citizen c = it7.next();
                String key = c.getMotherTongue();
                ArrayList<String> citizenNames = motherTongueMap.get(key);
                if (citizenNames == null) {
                    citizenNames = new ArrayList<>();
                }
                citizenNames.add(c.getFirstName() + " " + c.getLastName());
                motherTongueMap.put(key, citizenNames);
            }
            Iterator<Map.Entry<String, ArrayList<String>>> it8 = motherTongueMap.entrySet().iterator();
            while (it8.hasNext()) {
                Map.Entry<String, ArrayList<String>> value = it8.next();
                System.out.println(value);
            }
            System.out.println();
            Iterator<Map.Entry<String, ArrayList<String>>> it9 = motherTongueMap.entrySet().iterator();
            while (it9.hasNext()) {
                Map.Entry<String, ArrayList<String>> value = it9.next();
                System.out.println(value.getKey() + ": " + value.getValue().size());
            }
            System.out.println("\n");



            //Occupation
            TreeMap<String, HashSet<String>> occupationMap = new TreeMap<>();
            Iterator<Citizen> it10 = masterSet.iterator();
            while (it10.hasNext()) {
                Citizen c = it10.next();
                String key = c.getOccupation();
                HashSet<String> birthplaces = occupationMap.get(key);
                if (birthplaces == null) {
                    birthplaces = new HashSet<>();
                }
                birthplaces.add(c.getfBirthplace());
                occupationMap.put(key, birthplaces);
            }
            Iterator<Map.Entry<String, HashSet<String>>> it11 = occupationMap.entrySet().iterator();
            while (it11.hasNext()) {
                Map.Entry<String, HashSet<String>> value = it11.next();
                System.out.println(value);
            }
            System.out.println("\n");



            //Remarks
            TreeMap<String, HashSet<String>> remarksMap = new TreeMap<>();
            Iterator<Citizen> it12 = masterSet.iterator();
            while (it12.hasNext()) {
                Citizen c = it12.next();
                String key = c.getGender();
                HashSet<String> remarks = remarksMap.get(key);
                if (remarks == null) {
                    remarks = new HashSet<>();
                }
                remarks.add(c.getRemarks());
                remarksMap.put(key, remarks);
            }
            Iterator<Map.Entry<String, HashSet<String>>> it13 = remarksMap.entrySet().iterator();
            while (it13.hasNext()) {
                Map.Entry<String, HashSet<String>> value = it13.next();
                System.out.println(value);
            }
            System.out.println("\n");



            //Values of Properties
            TreeMap<String, TreeSet<Double>> valuesMap = new TreeMap<>();
            TreeMap<Citizen, Double> tempMap2 = new TreeMap<>();
            Iterator<Citizen> it14 = masterSet.iterator();
            while (it14.hasNext()) {
                Citizen c = it14.next();
                String value = c.getValue();
                if (value.charAt(0)=='0') {
                    value = value.substring(2);
                }
                if (value.equals(".") || value.equals("un")) {
                    value = "0";
                }
                if (value.contains("*")) {
                    value = value.substring(0, value.indexOf("*"));
                }
                if (value.contains("$")) {
                    value = value.substring(value.indexOf("$")+1);
                }
                if (value.contains(",")) {
                    value = value.substring(0, value.indexOf(",")) + value.substring(value.indexOf(",")+1);
                }
                if (value.contains("/") && value.contains(" ")) {
                    double whole = Double.valueOf(value.substring(0, value.indexOf(" ")));
                    value = value.substring(2);
                    double numer = Double.valueOf(value.substring(0,value.indexOf("/")));
                    double denom = Double.valueOf(value.substring(value.indexOf("/")+1));
                    double num = whole + (numer/denom);
                    value = String.valueOf(num);
                }
                else if (value.contains("/")) {
                    double numer = Double.valueOf(value.substring(0,value.indexOf("/")));
                    double denom = Double.valueOf(value.substring(value.indexOf("/")+1));
                    double num = numer/denom;
                    value = String.valueOf(num);
                }

                double valueDouble = Double.valueOf(value);
                tempMap2.put(c, valueDouble);
            }
            Iterator<Map.Entry<Citizen, Double>> it15 = tempMap2.entrySet().iterator();
            while (it15.hasNext()) {
                Citizen c = it15.next().getKey();
                double d = it15.next().getValue();
                String key = c.getRentOrOwn();
                TreeSet<Double> values = valuesMap.get(key);
                if (values == null) {
                    values = new TreeSet<>();
                }
                values.add(d);
                valuesMap.put(key, values);
            }
            Iterator<Map.Entry<String, TreeSet<Double>>> it16 = valuesMap.entrySet().iterator();
            while (it16.hasNext()) {
                Map.Entry<String, TreeSet<Double>> value = it16.next();
                System.out.println(value);
            }
            System.out.println("\n");



            //Custom TreeMap - Analyzing correlation between attending school and occupation
            TreeMap<String, ArrayList<String>> schoolMap = new TreeMap<>();
            Iterator<Citizen> it17 = masterSet.iterator();
            while (it17.hasNext()) {
                Citizen c = it17.next();
                String key = c.getOccupation();
                ArrayList<String> school = schoolMap.get(key);
                if (school == null) {
                    school = new ArrayList<>();
                }
                school.add(c.getSchool());
                schoolMap.put(key, school);
            }
            Iterator<Map.Entry<String, ArrayList<String>>> it18 = schoolMap.entrySet().iterator();
            while (it18.hasNext()) {
                Map.Entry<String, ArrayList<String>> value = it18.next();
              //  System.out.println(value);
                int yesCount = 0;
                int noCount = 0;
                for (String s : value.getValue()) {
                    if (s.equals("Yes")) {
                        yesCount++;
                    }
                    if (s.equals("No")) {
                        noCount++;
                    }
                }
                System.out.println(value.getKey() + ": " + yesCount + " Yes (attended school), " + noCount + " No (did not attend school)");
            }
            System.out.println();
            System.out.println("From this data, it appears that the teacher occupation has the highest percentage of individuals who have attended school");

        } catch (IOException io) {
            System.err.println("File does not exist");
        }
    }

    public static void main (String[] args) {
        FedCensusTask fedCensusTask = new FedCensusTask();
    }

    public class Citizen implements Comparable {

        private String firstName, lastName, street, num, relation, rentOrOwn, value, gender, age, status, ageFirstMar, school, read, birthplace, fBirthplace, mBirthplace, motherTongue, yearImmi, occupation, industry, remarks;

        public Citizen (String firstName, String lastName, String street, String num, String relation, String rentOrOwn, String value, String gender, String age, String status, String ageFirstMar, String school, String read, String birthplace, String fBirthplace, String mBirthplace, String motherTongue, String yearImmi, String occupation, String industry, String remarks) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.street = street;
            this.num = num;
            this.relation = relation;
            this.rentOrOwn = rentOrOwn;
            this.value = value;
            this.gender = gender;
            this.age = age;
            this.status = status;
            this.ageFirstMar = ageFirstMar;
            this.school = school;
            this.read = read;
            this.birthplace = birthplace;
            this.fBirthplace = fBirthplace;
            this.mBirthplace = mBirthplace;
            this.motherTongue = motherTongue;
            this.yearImmi = yearImmi;
            this.occupation = occupation;
            this.industry = industry;
            this.remarks = remarks;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getStreet() {
            return street;
        }

        public String getNum() {
            return num;
        }

        public String getRelation() {
            return relation;
        }

        public String getRentOrOwn() {
            return rentOrOwn;
        }

        public String getValue() {
            return value;
        }

        public String getGender() {
            return gender;
        }

        public String getAge() {
            return age;
        }

        public String getStatus() {
            return status;
        }

        public String getAgeFirstMar() {
            return ageFirstMar;
        }

        public String getSchool() {
            return school;
        }

        public String getRead() {
            return read;
        }

        public String getBirthplace() {
            return birthplace;
        }

        public String getfBirthplace() {
            return fBirthplace;
        }

        public String getmBirthplace() {
            return mBirthplace;
        }

        public String getMotherTongue() {
            return motherTongue;
        }

        public String getYearImmi() {
            return yearImmi;
        }

        public String getOccupation() {
            return occupation;
        }

        public String getIndustry() {
            return industry;
        }

        public String getRemarks() {
            return remarks;
        }

        @Override
        public String toString() {
            return "Citizen{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", street='" + street + '\'' +
                    ", num='" + num + '\'' +
                    ", relation='" + relation + '\'' +
                    ", rentOrOwn='" + rentOrOwn + '\'' +
                    ", value='" + value + '\'' +
                    ", gender='" + gender + '\'' +
                    ", age='" + age + '\'' +
                    ", status='" + status + '\'' +
                    ", ageFirstMar='" + ageFirstMar + '\'' +
                    ", school='" + school + '\'' +
                    ", read='" + read + '\'' +
                    ", birthplace='" + birthplace + '\'' +
                    ", fBirthplace='" + fBirthplace + '\'' +
                    ", mBirthplace='" + mBirthplace + '\'' +
                    ", motherTongue='" + motherTongue + '\'' +
                    ", yearImmi='" + yearImmi + '\'' +
                    ", occupation='" + occupation + '\'' +
                    ", industry='" + industry + '\'' +
                    ", remarks='" + remarks + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            return Comparator.comparing(Citizen::getFirstName).thenComparing(Citizen::getLastName).thenComparing(Citizen::getStreet).thenComparing(Citizen::getStreet).thenComparing(Citizen::getRelation).thenComparing(Citizen::getRentOrOwn).thenComparing(Citizen::getValue).thenComparing(Citizen::getGender).thenComparing(Citizen::getAge).thenComparing(Citizen::getStatus).thenComparing(Citizen::getAgeFirstMar).thenComparing(Citizen::getSchool).thenComparing(Citizen::getRead).thenComparing(Citizen::getBirthplace).thenComparing(Citizen::getfBirthplace).thenComparing(Citizen::getmBirthplace).thenComparing(Citizen::getMotherTongue).thenComparing(Citizen::getYearImmi).thenComparing(Citizen::getOccupation).thenComparing(Citizen::getIndustry).thenComparing(Citizen::getRemarks).compare(this, (Citizen) o);
        }
    }
}
