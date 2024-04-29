import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {
        
        // Considerando a lista abaixo execute as seguintes operações usando as features do Java 8+ (não utilize: for/while/if)

        List<Integer> numbers = Arrays.asList(1, 5, 8, 7, 4, 1, 6, 3, 2, 1, 8, 5, 7, 4);

        // 01- imprima todos os itens da lista no console
        numbers.forEach(n -> System.out.println(n));
            // ou
        numbers.forEach(System.out::println);


        // 02- imprima os números distintos em ordem crescente
        numbers.stream()
            .distinct()
            .sorted()
            .forEach(System.out::println);


        // 03- imprima os números impares  e distintos em ordem decrescente
        numbers.stream()
            .distinct()
            .filter(n -> n%2 != 0)
            .forEach(System.out::println);


        // 04. imprima a soma dos números ignorando os 3 primeiros
        System.out.println(
            numbers.stream()
                .skip(3)
                .reduce(0, Integer::sum)
        );


        // 05. imprima cada número da lista multiplicado por 2
        numbers.forEach(n -> System.out.println(n*2));


        // 06. imprima de forma agrupada os números pares e impares distintos
        numbers.stream()
            .distinct()
            .collect(Collectors.partitioningBy(n -> n%2 ==0, Collectors.toList()))
            .forEach((parity, n) -> System.out.println(n));


        // 07. imprima o maior número da lista, se não houver lance uma java.util.NoSuchElementException
        System.out.println(
            numbers.stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt()
        );


        // 08. imprima apenas os 3 primeiros números da lista
        numbers.stream()
            .limit(3)
            .forEach(System.out::println);


        //  09. imprima a média dos números da lista, se não for possível calcular a média imprima Double.NaN
        System.out.println(
            numbers.stream()
                .mapToInt(Integer::intValue)
                .average()
                .getAsDouble()
        );
    }
}
