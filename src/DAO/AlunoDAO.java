package DAO;

import Vo.AlunoVo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

public class AlunoDAO {

    public void incluir() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        AlunoVo aluno = new AlunoVo();

        try {
            String nome = JOptionPane.showInputDialog(null, "Digite o nome do aluno: ");
            float nota1 = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite a nota1 do aluno: "));
            float nota2 = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite a nota2 do aluno: "));
            float nota3 = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite a nota3 do aluno: "));
            float nota4 = Float.parseFloat(JOptionPane.showInputDialog(null, "Digite a nota4 do aluno: "));

            aluno.setNome(nome);
            aluno.setNota1(nota1);
            aluno.setNota2(nota2);
            aluno.setNota3(nota3);
            aluno.setNota4(nota4);

            emf = Persistence.createEntityManagerFactory("UnidadeAlunos");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
            System.out.println("Inclusão realizada com sucesso");

        } catch (Exception ex) {
            System.out.println("Inclusão não realizada " + ex.getMessage());
        } finally {

            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

    public void alterar() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        AlunoVo aluno = new AlunoVo();

        try {
            emf = Persistence.createEntityManagerFactory("UnidadeAlunos");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            int matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a matricula do aluno a ser alterado: "));

            Query consulta = em.createQuery("SELECT av FROM AlunoVo av WHERE av.matricula =:matricula");
            consulta.setParameter("matricula", matricula);

            List<AlunoVo> lista = consulta.getResultList();

            if (lista.size() > 0) {
                aluno = lista.get(0);

                String nome = JOptionPane.showInputDialog("Digite o nome do aluno: ", aluno.getNome());
                float nota1 = Float.parseFloat(JOptionPane.showInputDialog("Digite a nota1 do aluno: ", aluno.getNota1()));
                float nota2 = Float.parseFloat(JOptionPane.showInputDialog("Digite a nota2 do aluno: ", aluno.getNota2()));
                float nota3 = Float.parseFloat(JOptionPane.showInputDialog("Digite a nota3 do aluno: ", aluno.getNota3()));
                float nota4 = Float.parseFloat(JOptionPane.showInputDialog("Digite a nota4 do aluno: ", aluno.getNota4()));

                aluno.setNome(nome);
                aluno.setNota1(nota1);
                aluno.setNota2(nota2);
                aluno.setNota3(nota3);
                aluno.setNota4(nota4);

                em.merge(aluno);
                em.getTransaction().commit();
                System.out.println("Alteração feita com sucesso!! ");

            }

        } catch (Exception ex) {
            System.out.println("Erro ao alterar aluno " + ex.getMessage());
        } finally {

            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

    public void excluir() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        AlunoVo aluno = new AlunoVo();

        try {
            emf = Persistence.createEntityManagerFactory("UnidadeAlunos");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            int matricula = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a matricula do aluno a ser alterado: "));

            Query consulta = em.createQuery("SELECT av FROM AlunoVo av WHERE = :matricula");
            consulta.setParameter("matricula", matricula);
            List<AlunoVo> lista = consulta.getResultList();

            if (lista.size() > 0) {
                aluno = lista.get(0);
                em.remove(aluno);
                em.getTransaction().commit();
                System.out.println("Exclusão realizada com sucesso!!");                
            }          
            
        } catch (Exception ex) {
            System.out.println("Erro ao exluir " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }

    public void alunosCadastrados() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        AlunoVo aluno = new AlunoVo();

        try {
            emf = Persistence.createEntityManagerFactory("UnidadeAlunos");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Query consulta = em.createQuery("SELECT av FROM AlunoVo av");

            List<AlunoVo> lista = consulta.getResultList();

            if (lista.size() > 0) {

                for (int i = 0; i < lista.size(); i++) {

                    aluno = lista.get(i);

                    float nota1, nota2, nota3, nota4;
                    float media;

                    nota1 = aluno.getNota1();
                    nota2 = aluno.getNota2();
                    nota3 = aluno.getNota3();
                    nota4 = aluno.getNota4();

                    media = (nota1 + nota2 + nota3 + nota4) / 4;

                    System.out.println("Nome: " + aluno.getNome() + " Matricula: " + aluno.getMatricula() + " Media: " + media);
                }
            }

        } catch (Exception ex) {
            System.out.println("Erro ao buscar aluno " + ex.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}
