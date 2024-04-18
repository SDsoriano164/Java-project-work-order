/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import java.awt.Color;
import java.sql.*; // importanto connector
import br.com.infox.dal.ModuloConexao; // importando o modulo criado para conexcao com o banco
import javax.swing.JOptionPane;

/**
 *
 * @author phstr
 */
public class TelaUsuario extends javax.swing.JInternalFrame { // usa os campos e atributos da clase JinternalFrame

    // variaveis criada para fazer a conexcao e pegar os dados do bdd // 
    // sempre que for solicitado dados do bancos de dados, tem que ser feito isso.
    Connection conexao = null;

    PreparedStatement pst = null;

    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    public TelaUsuario() { // construtor
        initComponents();
        getContentPane().setBackground(new Color(240, 255, 240)); // coloca cor no background
        // chamando a conexcao do modulo conector
        conexao = ModuloConexao.conector();

    }

    private void consultar() { // CRUD - READ

        // variavel sql armazendando a consulta na variavel ( cria a query )
        String sql = "select * from tbusuarios where iduser=?"; // ? campo do bdd

        try {
            // prepara a conexao
            pst = conexao.prepareStatement(sql);
            // seta o valor da variavel para o campo solicitado
            pst.setString(1, txtuserID.getText());  // primeiro parametro da consulta // vai retornar o valor digitado equivalente
            // se criarmos outro parametro ele só vai executar da forma correta se definirmos igual ao bdd

            // executa a query
            rs = pst.executeQuery();
            // bloco de codigo se der tudo certo
            if (rs.next()) {
                // mostrar os textos 
                txtuserNome.setText(rs.getString(2)); // seta os dados cadastrado na tbusuarios
                txtuserLogin.setText(rs.getString(4));
                txtuserFone.setText(rs.getString(3));
                txtuserSenha.setText(rs.getString(5));
                cbUsuPerfil.setSelectedItem(rs.getString(6));

            } else {
                // mostra mensagem de erro se nao for encontrato
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado !");
                // limpa os campos
                limparCampos();

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    } // fim do metodo consultar

    private void adicionar() { // metodo para adicionar usuarios CRUD - CREATE

        //armazenando dados da consulta do bdd   // codigo mysql para atribuir os valores a string
        String sql = "insert into tbusuarios (iduser,usuario,fone,login,senha,perfil)values(?,?,?,?,?,?)";

        try {
            // preparando conexcao
            pst = conexao.prepareStatement(sql);

            // pega os dados da conexcao do campos.
            pst.setString(1, txtuserID.getText()); // passando os parametros 1 -iduser
            pst.setString(2, txtuserNome.getText());
            pst.setString(3, txtuserFone.getText());
            pst.setString(4, txtuserLogin.getText());
            pst.setString(5, txtuserSenha.getText());
            pst.setString(6, cbUsuPerfil.getSelectedItem().toString());

            if (txtuserID.getText().isEmpty() || txtuserNome.getText().isEmpty() || txtuserLogin.getText().isEmpty() || txtuserSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios !");

            } else {

                // a linha abaixo atualiza a tbusuarios com os dados do formulario    
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cadastrado com sucesso !");
                    limparCampos();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    // criando metodo para alterar dados do usuario  Crud - update
    public void alterar() {
        String sql = "update tbusuarios set usuario = ?, fone = ?, login = ?, senha = ?, perfil = ? where iduser = ?";
        try {
            // preparando conexao
            pst = conexao.prepareStatement(sql);

            // pega os dados inseridos na tabela e passa para o campos do banco
            pst.setString(1, txtuserNome.getText());
            pst.setString(2, txtuserFone.getText());
            pst.setString(3, txtuserLogin.getText());
            pst.setString(4, txtuserSenha.getText());
            pst.setString(5, cbUsuPerfil.getSelectedItem().toString());
            pst.setString(6, txtuserID.getText());

            // teste para verificar se os dados obrigatórios foram todos preenchidos.
            if (txtuserID.getText().isEmpty() || txtuserNome.getText().isEmpty() || txtuserLogin.getText().isEmpty() || txtuserSenha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios !");

            } else {

                // a estrutura abaixo serve para confirmar a alteração dos dados da tabela
                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário atualizado com sucesso !");
                    limparCampos();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        

    }
    public void deletar(){ // CRUD - DELETE
        int confirma = JOptionPane.showConfirmDialog(null,"Tem certeza que deseja remover este usuário ?","ATENÇÃO",JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION){
            String sql = "delete from tbusuarios where iduser = ?";
           
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtuserID.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuario removido com sucesso !");
                limparCampos();
                
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }        
    }
        
        
    

    private void limparCampos() {
        txtuserID.setText(null);
        txtuserNome.setText(null);
        txtuserLogin.setText(null);
        txtuserFone.setText(null);
        txtuserSenha.setText(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtuserID = new javax.swing.JTextField();
        txtuserNome = new javax.swing.JTextField();
        txtuserLogin = new javax.swing.JTextField();
        txtuserFone = new javax.swing.JTextField();
        cbUsuPerfil = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtuserSenha = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();
        btnCreate = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Usuários");
        setName("Usuário"); // NOI18N
        setPreferredSize(new java.awt.Dimension(866, 662));

        jLabel1.setText("*ID");

        jLabel2.setText("*Nome");

        jLabel3.setText("*Login");

        jLabel4.setText("*Senha");

        jLabel5.setText("*Perfil");

        txtuserNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserNomeActionPerformed(evt);
            }
        });

        txtuserLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserLoginActionPerformed(evt);
            }
        });

        txtuserFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserFoneActionPerformed(evt);
            }
        });

        cbUsuPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        cbUsuPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbUsuPerfilActionPerformed(evt);
            }
        });

        jLabel6.setText("*Fone ");

        txtuserSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtuserSenhaActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Delete.png"))); // NOI18N
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Create.png"))); // NOI18N
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Read.png"))); // NOI18N
        btnRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReadActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/Update.png"))); // NOI18N
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        jLabel7.setText("* Campos Obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(226, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(59, 59, 59)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtuserID, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtuserNome, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCreate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addComponent(btnRead, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4))
                                        .addGap(56, 56, 56)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtuserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtuserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51)
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(51, 51, 51))
                                    .addComponent(txtuserFone, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(202, 202, 202))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(125, 125, 125))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(312, 312, 312))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(312, 312, 312))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtuserID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtuserNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtuserFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtuserSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbUsuPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(txtuserLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRead, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121))
        );

        getAccessibleContext().setAccessibleDescription("");

        setBounds(0, 0, 866, 662);
    }// </editor-fold>//GEN-END:initComponents

    private void txtuserNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuserNomeActionPerformed

    private void txtuserLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuserLoginActionPerformed

    private void txtuserFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserFoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuserFoneActionPerformed

    private void txtuserSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtuserSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtuserSenhaActionPerformed

    private void cbUsuPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbUsuPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbUsuPerfilActionPerformed

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReadActionPerformed
        // INSERT
        consultar();
    }//GEN-LAST:event_btnReadActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        // CREATE
        //chamando o metodo adicionar
        adicionar();
    }//GEN-LAST:event_btnCreateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // UPDATE
        // chamando o metodo alterar
        alterar();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // DELETE
        // chamando o metodo remover
        deletar();
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbUsuPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtuserFone;
    private javax.swing.JTextField txtuserID;
    private javax.swing.JTextField txtuserLogin;
    private javax.swing.JTextField txtuserNome;
    private javax.swing.JTextField txtuserSenha;
    // End of variables declaration//GEN-END:variables
}
