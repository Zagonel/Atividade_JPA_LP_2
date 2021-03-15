package Main;

import DAO.AlunoDAO;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void menuPrincipal() {
        int opcao = 0;
        AlunoDAO aluno = new AlunoDAO();

        try {
            opcao = Integer.valueOf(JOptionPane.showInputDialog(null, "|----------------------- Menu Principal ---------------------------|\n"
                    + "|  1 - Incluir\n"
                    + "|  2 - Alterar\n"
                    + "|  3 - Excluir\n"
                    + "|  4 - AlunosCadastrados\n"
                    + "|  5 - Sair\n"
                    + "|--------------------------------------------------------------|\n\n"
                    + "Digite a opção requerida:"));
        } catch (NumberFormatException e) {
            if (e.getMessage().equals("null")) {
                System.exit(0);
            }
            JOptionPane.showMessageDialog(null, "Digite uma opção valida !! " + e.getMessage());
            menuPrincipal();
        }

        switch (opcao) {
            case 1:
                aluno.incluir();
                menuPrincipal();
                break;
            case 2:
                aluno.alterar();
                menuPrincipal();
                break;
            case 3:
                aluno.excluir();
                menuPrincipal();
                break;
            case 4:
                aluno.alunosCadastrados();
                menuPrincipal();
                break;
            case 5:
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(null, "\n Opção invalida tente novamente!!");
                menuPrincipal();
                break;
        }
    }
}
