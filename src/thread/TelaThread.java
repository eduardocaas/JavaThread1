package src.thread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class TelaThread extends JDialog {

    private JPanel jPanel = new JPanel(new GridBagLayout()); //painel de componentes
    private JLabel desc1 = new JLabel("Nome");
    private JTextField campoTexto1 = new JTextField();
    private JLabel desc2 = new JLabel("E-mail");
    private JTextField campoTexto2 = new JTextField();
    private JButton jButton = new JButton("Adicionar");
    private JButton jButton2 = new JButton("Parar");

    private FilaThread fila = new FilaThread();

    public TelaThread(){

        setTitle("Threads");
        setSize(new Dimension(240,240));
        setLocationRelativeTo(null);
        setResizable(false);
        //Primeira parte

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2; //cria com 2 posições
        gridBagConstraints.insets = new Insets(5, 10, 5 , 5); //semelhante ao padding CSS
        gridBagConstraints.anchor = gridBagConstraints.WEST; //orienta elementos

        desc1.setPreferredSize(new Dimension(200, 25));
        jPanel.add(desc1, gridBagConstraints);

        campoTexto1.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(campoTexto1, gridBagConstraints);

        desc2.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(desc2, gridBagConstraints);

        campoTexto2.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(campoTexto2, gridBagConstraints);

        gridBagConstraints.gridwidth = 1; //retorna o valor de 1 para o conteudo abaixo

        jButton.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridy++;
        jPanel.add(jButton, gridBagConstraints);

        jButton2.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridx++;
        jPanel.add(jButton2, gridBagConstraints);

        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) { //executa o clique no botao

                if(fila == null){
                    fila = new FilaThread();
                    fila.start();
                }
                for(int qtd = 0; qtd <= 100; qtd++) {

                    ObjetoThread filaThread = new ObjetoThread();
                    filaThread.setNome(campoTexto1.getText() + " - " + qtd);
                    filaThread.setEmail(campoTexto2.getText());

                    fila.add(filaThread);
                    jButton2.setEnabled(true);

                }
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fila.stop();
                fila = null;
                jButton.setText("Continuar");
                jButton2.setEnabled(false);
            }
        });

        fila.start();

        add(jPanel, BorderLayout.WEST);
        setVisible(true); //ultimo a ser executado
    }

}
