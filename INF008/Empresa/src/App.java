import rh.Chefe;
import rh.Empregado;
import rh.Empresa;

public class App {
    public static void main(String[] args) throws Exception {
        Chefe CEO = new Chefe("Ronaldo", 35, null);
        Empresa DesNatural = new Empresa("DesNatural", CEO);

        Chefe chef_1 = new Chefe("Marcos", 41, CEO);
        Chefe chef_2 = new Chefe("Ana Clara", 24, CEO);
        CEO.addSubordinado(chef_1);
        CEO.addSubordinado(chef_2);
        
        Empregado emp_1 = new Empregado("João", 19, chef_2);
        Empregado emp_2 = new Empregado("Ana", 23, chef_2);
        chef_2.addSubordinado(emp_1);
        chef_2.addSubordinado(emp_2);
        
        Chefe chef_3 = new Chefe("Carlos", 51, chef_1);
        Empregado emp_3 = new Empregado("Mario", 51, chef_1);
        chef_1.addSubordinado(chef_3);
        chef_1.addSubordinado(emp_3);
        
        Empregado emp_4 = new Empregado("Marta", 53, chef_3);
        chef_3.addSubordinado(emp_4);

        DesNatural.addChefe(chef_1);
        DesNatural.addChefe(chef_2);
        DesNatural.addChefe(chef_3);

        DesNatural.addEmpregado(emp_1);
        DesNatural.addEmpregado(emp_2);
        DesNatural.addEmpregado(emp_3);
        DesNatural.addEmpregado(emp_4);

        System.out.println(emp_4.getListaSuperiores());
        System.out.println(chef_2.getListaEmpregadosAssociados());
        System.out.println(CEO.getListaEmpregadosAssociados());
        // System.out.println(chef_1.getListaSubordinadosDiretos());
    }
}
