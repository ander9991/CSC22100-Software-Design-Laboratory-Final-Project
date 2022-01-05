package sample;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;

public class HistogramAlphabet {
    public Map<String, Integer> characterFrequency = new HashMap<>();

    public HistogramAlphabet() {
    } // Will create an empty histogram

    public HistogramAlphabet(Map<String, Integer> frequency) {
        characterFrequency.putAll(frequency);
        // Add all entries into the histogram
    }

    public HistogramAlphabet(String s) {
        String temp = s.replaceAll("[^a-zA-z]", "").toLowerCase();
        // https://www.javatpoint.com/java-string-replaceall
        for (int i = 0; i < temp.length(); i++) {
            String key = temp.charAt(i) + "";
            incrementFrequency(characterFrequency, key);
        }
    }

    public Map<String, Integer> getFrequency() {
        return characterFrequency;
    }
    public Integer getCumulativeFrequency() {
        return characterFrequency.values().stream().reduce(0, Integer::sum);
    }

    // Sort in increasing frequency order
    public Map<String, Integer> sortIncreasing() {
        return characterFrequency.entrySet().stream().sorted(Map.Entry.comparingByValue()).
                collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    // Sort in decreasing frequency order
    public Map<String, Integer> sortDecreasing() {
        return characterFrequency.entrySet().stream().sorted(Collections.reverseOrder
                (Map.Entry.comparingByValue())).
                collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e2,
                        LinkedHashMap::new));
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Frequency of Characters\n\n");
        for (String K : characterFrequency.keySet()) {
            output.append(K).append(": ").append(characterFrequency.get(K)).append("\n");
        }
        return output.toString();
    }

    private static <K> void incrementFrequency(Map<K, Integer> map, K key) {
        map.putIfAbsent(key, 0);
        map.put(key, map.get(key) + 1);
    }

    public class MyPieChart  {
        //map for probabilities
        private Map<String, Double> probability = new HashMap<String, Double>();
        //map for the slices of the pie chart
        private Map<String, Slice> slices = new HashMap<String, Slice>();
        private Map<Character, Integer> data = new HashMap<Character, Integer>(); // TODO
        int num; //number of characters displayed
        MyPoint center; //center of pie chart
        double radius; //for the radius of the chart
        double startingAngle;
        //for the starting (rotating angle) of
        //the first slide of the pie chart

        public MyPieChart(int num, MyPoint center, double radius, double startingAngle) {
            this.num = num;
            this.center = center;
            this.radius = radius;
            this.startingAngle = Optional.ofNullable(startingAngle).orElse(0.0);
            //in case the angle value is null
            //if it is set it to zero

            probability = getProbability();
            slices = createPieChart();
        }

        public MyPieChart(int num, MyPoint center, double radius, Map<Character, Integer> grades){
            this.num = num;
            this.center = center;
            this.radius = radius;
            data = grades; // TODO

        }

        public Map<String, Double> getProbability() {
            //character probabilities are ordered by the key
            double cumulativeFrequency = 1.0 / getCumulativeFrequency();
            for (String key : characterFrequency.keySet()) {
                probability.put(key, (double) characterFrequency.get(key) * cumulativeFrequency);
            }
            return probability.entrySet().stream().sorted(Collections.
                    reverseOrder(Map.Entry.comparingByValue())).
                    collect(Collectors.toMap(Map.Entry::getKey,
                            Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        }

        public double getSumOfProbability() {
            return probability.values().stream().reduce(0.0, Double::sum);
        }

        public Map<String, Slice> createPieChart() {

            MyColor[] colors = MyColor.getMyColors();
            int randomColor = 0;
            for(MyColor c : MyColor.values()){
                colors[randomColor] = c;
            }
            // No Colors will be reused
            double startAngle = startingAngle;
            int counter = 0;
            double probabilityCounter = 0;
            for (String key : probability.keySet()) {
                if (counter < num){
                    double angle = 360.0 * probability.get(key);
                    slices.put(key, new Slice(center, radius, startAngle, angle, colors[randomColor]));
                    startAngle += angle;
                    counter++;
                    randomColor++;
                }
                else {
                    double angle = 360.0 * (1 - probabilityCounter);
                    slices.put("All other letters", new Slice(center, radius, startAngle, angle, colors[randomColor]));
                    break;
                }
                probabilityCounter += probability.get(key);
            }
            return slices;
        }

        public void draw(GraphicsContext gc){
            double probCounter;
            DecimalFormat df = new DecimalFormat("#.####"); // 4 decimal places
            df.setRoundingMode(RoundingMode.CEILING);
            for (String key : slices.keySet()) {
                probCounter = slices.get(key).angleInRadians() / (2*Math.PI);
                double x = 1.3 * radius * Math.cos(Math.toRadians(slices.get(key).getCentralAngle()));
                double y = 1.3 * radius * Math.sin(Math.toRadians(slices.get(key).getCentralAngle()));
                slices.get(key).draw(gc);
                String letter = key + " = " + df.format(probCounter);
                gc.setTextAlign(TextAlignment.CENTER);
                gc.strokeText(letter, center.getX() + x, center.getY() - y);
            }
        }
    }
}
