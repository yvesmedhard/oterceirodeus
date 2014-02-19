
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Yves
 */
public class CriacaodoPersonagem extends javax.swing.JFrame {

    /**
     * Creates new form CriacaodoPersonagem
     */
    ArrayList<Raça> racas = new ArrayList();
    ArrayList<Classe> classes = new ArrayList();
    GameCore mf3d;
    Player player;

    public CriacaodoPersonagem() {
        initComponents();
        getRacas();
    }

    public CriacaodoPersonagem(GameCore mf3d) {
        initComponents();
        player = new Player();
        player.setBackMusic("Hack alpha vision.mp3").play();
        this.mf3d = mf3d;
        getRacas();
    }

    public void getRacas() {
        racas = new Raça().gerarRacas();
        for (Raça r : racas) {
            jComboBox1.addItem(r.getNome() + " (" + r.getCusto() + ")");
        }
    }

    public void getClasses(Raça r) {
        classes = r.getClasses();
        jComboBox2.removeAllItems();
        jComboBox2.addItem("Escolha a Classe");
        for (Classe c : r.getClasses()) {
            jComboBox2.addItem(c.getNome() + " (" + c.getCusto() + ")");
        }
    }

    public void getClasses() {
        jComboBox2.removeAllItems();
        jComboBox2.addItem("Escolha a Classe");
    }

    public void zerarValores() {
        jLabel7.setText("0");
        jLabel8.setText("0");
        jLabel9.setText("0");
        jLabel10.setText("0");
        jLabel11.setText("0");

        jLabel32.setText("0");
        jLabel33.setText("0");
        jLabel34.setText("0");
        jLabel35.setText("0");
        jLabel36.setText("0");
        jLabel44.setText("0");

        jLabel37.setText("10");

        list1.removeAll();
        list2.removeAll();
    }

    public void setarValoresRaciais(Raça r) {
        jLabel7.setText(Integer.toString(Integer.parseInt(jLabel7.getText()) + r.getAtributos().get("Força")));
        jLabel8.setText(Integer.toString(Integer.parseInt(jLabel8.getText()) + r.getAtributos().get("Habilidade")));
        jLabel9.setText(Integer.toString(Integer.parseInt(jLabel9.getText()) + r.getAtributos().get("Resistência")));
        jLabel10.setText(Integer.toString(Integer.parseInt(jLabel10.getText()) + r.getAtributos().get("Armadura")));
        jLabel11.setText(Integer.toString(Integer.parseInt(jLabel11.getText()) + r.getAtributos().get("PdF")));

        jLabel32.setText(Integer.toString(Integer.parseInt(jLabel32.getText()) + r.getFocus().get("Água")));
        jLabel33.setText(Integer.toString(Integer.parseInt(jLabel33.getText()) + r.getFocus().get("Terra")));
        jLabel34.setText(Integer.toString(Integer.parseInt(jLabel34.getText()) + r.getFocus().get("Fogo")));
        jLabel35.setText(Integer.toString(Integer.parseInt(jLabel35.getText()) + r.getFocus().get("Ar")));
        jLabel36.setText(Integer.toString(Integer.parseInt(jLabel36.getText()) + r.getFocus().get("Luz")));
        jLabel44.setText(Integer.toString(Integer.parseInt(jLabel44.getText()) + r.getFocus().get("Trevas")));

        for (String[] vantagem : r.getVantagens()) {
            list1.add(vantagem[0]);
        }
        for (String[] desvantagem : r.getDesvantagens()) {
            list2.add(desvantagem[0]);
        }
    }

    public void setarValoresdeClasse(Classe c) {
        jLabel7.setText(Integer.toString(Integer.parseInt(jLabel7.getText()) + c.getAtributos().get("Força")));
        jLabel8.setText(Integer.toString(Integer.parseInt(jLabel8.getText()) + c.getAtributos().get("Habilidade")));
        jLabel9.setText(Integer.toString(Integer.parseInt(jLabel9.getText()) + c.getAtributos().get("Resistência")));
        jLabel10.setText(Integer.toString(Integer.parseInt(jLabel10.getText()) + c.getAtributos().get("Armadura")));
        jLabel11.setText(Integer.toString(Integer.parseInt(jLabel11.getText()) + c.getAtributos().get("PdF")));

        jLabel32.setText(Integer.toString(Integer.parseInt(jLabel32.getText()) + c.getFocus().get("Água")));
        jLabel33.setText(Integer.toString(Integer.parseInt(jLabel33.getText()) + c.getFocus().get("Terra")));
        jLabel34.setText(Integer.toString(Integer.parseInt(jLabel34.getText()) + c.getFocus().get("Fogo")));
        jLabel35.setText(Integer.toString(Integer.parseInt(jLabel35.getText()) + c.getFocus().get("Ar")));
        jLabel36.setText(Integer.toString(Integer.parseInt(jLabel36.getText()) + c.getFocus().get("Luz")));
        jLabel44.setText(Integer.toString(Integer.parseInt(jLabel44.getText()) + c.getFocus().get("Trevas")));

        for (String[] vantagem : c.getVantagens()) {
            list1.add(vantagem[0]);
        }
        for (String[] desvantagem : c.getDesvantagens()) {
            list2.add(desvantagem[0]);
        }
    }

    public void travarControles(boolean b) {
        jButton2.setEnabled(b);
        jButton3.setEnabled(b);
        jButton4.setEnabled(b);
        jButton5.setEnabled(b);
        jButton6.setEnabled(b);
        jButton7.setEnabled(b);
        jButton8.setEnabled(b);
        jButton9.setEnabled(b);
        jButton10.setEnabled(b);
        jButton11.setEnabled(b);
        jButton12.setEnabled(b);
        jButton13.setEnabled(b);
        jButton14.setEnabled(b);
        jButton15.setEnabled(b);
        jButton16.setEnabled(b);
        jButton17.setEnabled(b);
        jButton18.setEnabled(b);
        jButton19.setEnabled(b);
        jButton20.setEnabled(b);
        jButton21.setEnabled(b);
        jButton22.setEnabled(b);
        jButton23.setEnabled(b);
        jButton24.setEnabled(b);

        jComboBox1.setEnabled(b);
        jComboBox2.setEnabled(b);

        textField1.setEnabled(b);

        list1.setEnabled(b);
        list2.setEnabled(b);
    }

    public Ficha criarFicha() {

        Raça r = racas.get(jComboBox1.getSelectedIndex() - 1);
        Classe c = classes.get(jComboBox2.getSelectedIndex() - 1);

        r.setAtributos(Integer.parseInt(jLabel7.getText()) - c.getAtributos().get("Força"),
                Integer.parseInt(jLabel8.getText()) - c.getAtributos().get("Habilidade"),
                Integer.parseInt(jLabel9.getText()) - c.getAtributos().get("Resistência"),
                Integer.parseInt(jLabel10.getText()) - c.getAtributos().get("Armadura"),
                Integer.parseInt(jLabel11.getText()) - c.getAtributos().get("PdF"));
        r.setFocus(Integer.parseInt(jLabel32.getText()) - c.getFocus().get("Água"),
                Integer.parseInt(jLabel33.getText()) - c.getFocus().get("Terra"),
                Integer.parseInt(jLabel34.getText()) - c.getFocus().get("Fogo"),
                Integer.parseInt(jLabel35.getText()) - c.getFocus().get("Ar"),
                Integer.parseInt(jLabel36.getText()) - c.getFocus().get("Luz"),
                Integer.parseInt(jLabel44.getText()) - c.getFocus().get("Trevas"));

        return new Ficha(textField1.getText(), r, c);


    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        textField1 = new java.awt.TextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        list1 = new java.awt.List();
        list2 = new java.awt.List();
        jPanel5 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ficha de Personagem");
        setBackground(new java.awt.Color(153, 153, 153));
        setBounds(new java.awt.Rectangle(150, 100, 500, 590));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        setResizable(false);

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Ficha De Personagem");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        jLabel27.setBackground(new java.awt.Color(204, 153, 255));
        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("Nome");
        jLabel27.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel27.setOpaque(true);
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a Raça" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox2PropertyChange(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Escolha a Classe" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox2PropertyChange(evt);
            }
        });

        textField1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        textField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel37.setBackground(new java.awt.Color(204, 204, 204));
        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("10");
        jLabel37.setOpaque(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel27, textField1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox2});

        jComboBox1.getAccessibleContext().setAccessibleName("Raça");
        jComboBox2.getAccessibleContext().setAccessibleName("Classe");
        jLabel37.getAccessibleContext().setAccessibleName("Pontos");

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Atributos");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(255, 102, 51));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Força");
        jLabel2.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel2.setOpaque(true);
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(204, 102, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Armadura");
        jLabel3.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel3.setOpaque(true);
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(0, 204, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Resistência");
        jLabel4.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel4.setOpaque(true);
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel5.setBackground(new java.awt.Color(153, 153, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Poder de Fogo");
        jLabel5.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel5.setOpaque(true);
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(102, 153, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Habilidade");
        jLabel6.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel6.setOpaque(true);
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("0");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("0");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("0");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("0");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("PVs: ");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("PMs: ");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton3.setText("-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton4.setText("+");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("-");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton6.setText("+");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton7.setText("-");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton8.setText("+");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton9.setText("-");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton10.setText("+");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButton11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton11.setText("-");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton12.setText("+");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(6, 6, 6))))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton3, jButton5});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6});

        jButton3.getAccessibleContext().setAccessibleName("Força");
        jButton3.getAccessibleContext().setAccessibleDescription("-");
        jButton4.getAccessibleContext().setAccessibleName("Força");
        jButton4.getAccessibleContext().setAccessibleDescription("+");
        jButton5.getAccessibleContext().setAccessibleName("Habilidade");
        jButton5.getAccessibleContext().setAccessibleDescription("-");
        jButton6.getAccessibleContext().setAccessibleName("Habilidade");
        jButton6.getAccessibleContext().setAccessibleDescription("+");
        jButton7.getAccessibleContext().setAccessibleName("Resistência");
        jButton7.getAccessibleContext().setAccessibleDescription("-");
        jButton8.getAccessibleContext().setAccessibleName("Resistência");
        jButton8.getAccessibleContext().setAccessibleDescription("+");
        jButton9.getAccessibleContext().setAccessibleName("Armadura");
        jButton9.getAccessibleContext().setAccessibleDescription("-");
        jButton10.getAccessibleContext().setAccessibleName("Armadura");
        jButton10.getAccessibleContext().setAccessibleDescription("+");
        jButton11.getAccessibleContext().setAccessibleName("Poder de Fogo");
        jButton11.getAccessibleContext().setAccessibleDescription("-");
        jButton12.getAccessibleContext().setAccessibleName("Poder de Fogo");
        jButton12.getAccessibleContext().setAccessibleDescription("+");

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Caminhos da Magia");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel13.setBackground(new java.awt.Color(102, 102, 255));
        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Água");
        jLabel13.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel13.setOpaque(true);
        jLabel13.setPreferredSize(new java.awt.Dimension(24, 33));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel15.setBackground(new java.awt.Color(153, 0, 0));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Terra");
        jLabel15.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel15.setOpaque(true);
        jLabel15.setPreferredSize(new java.awt.Dimension(24, 33));
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel17.setBackground(new java.awt.Color(255, 0, 51));
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Fogo");
        jLabel17.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel17.setOpaque(true);
        jLabel17.setPreferredSize(new java.awt.Dimension(24, 33));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(153, 255, 153));
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Ar");
        jLabel19.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel19.setOpaque(true);
        jLabel19.setPreferredSize(new java.awt.Dimension(24, 33));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(255, 255, 204));
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Luz");
        jLabel21.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel21.setOpaque(true);
        jLabel21.setPreferredSize(new java.awt.Dimension(24, 33));
        jLabel21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel23.setBackground(new java.awt.Color(102, 102, 102));
        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Trevas");
        jLabel23.setBorder(new javax.swing.border.MatteBorder(null));
        jLabel23.setOpaque(true);
        jLabel23.setPreferredSize(new java.awt.Dimension(24, 33));
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("0");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("0");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("0");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("0");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("0");

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("0");

        jButton13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton13.setText("-");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton14.setText("+");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton15.setText("-");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton16.setText("+");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton17.setText("-");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton18.setText("+");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButton19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton19.setText("-");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton20.setText("+");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButton21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton21.setText("-");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton22.setText("+");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        jButton23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton23.setText("-");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMinusActionPerformed(evt);
            }
        });

        jButton24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton24.setText("+");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPlusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton18)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton20)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel13, jLabel15, jLabel17, jLabel19, jLabel21});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(jButton22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel13, jLabel15, jLabel17, jLabel19, jLabel21, jLabel23});

        jButton13.getAccessibleContext().setAccessibleName("Terra");
        jButton13.getAccessibleContext().setAccessibleDescription("-");
        jButton14.getAccessibleContext().setAccessibleName("Terra");
        jButton14.getAccessibleContext().setAccessibleDescription("+");
        jButton15.getAccessibleContext().setAccessibleName("Fogo");
        jButton15.getAccessibleContext().setAccessibleDescription("-");
        jButton16.getAccessibleContext().setAccessibleName("Fogo");
        jButton16.getAccessibleContext().setAccessibleDescription("+");
        jButton17.getAccessibleContext().setAccessibleName("Ar");
        jButton17.getAccessibleContext().setAccessibleDescription("-");
        jButton18.getAccessibleContext().setAccessibleName("Ar");
        jButton18.getAccessibleContext().setAccessibleDescription("+");
        jButton19.getAccessibleContext().setAccessibleName("Luz");
        jButton19.getAccessibleContext().setAccessibleDescription("-");
        jButton20.getAccessibleContext().setAccessibleName("Luz");
        jButton20.getAccessibleContext().setAccessibleDescription("+");
        jButton21.getAccessibleContext().setAccessibleName("Trevas");
        jButton21.getAccessibleContext().setAccessibleDescription("-");
        jButton22.getAccessibleContext().setAccessibleName("Trevas");
        jButton22.getAccessibleContext().setAccessibleDescription("+");
        jButton23.getAccessibleContext().setAccessibleName("Água");
        jButton23.getAccessibleContext().setAccessibleDescription("-");
        jButton24.getAccessibleContext().setAccessibleName("Água");
        jButton24.getAccessibleContext().setAccessibleDescription("+");

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel28.setBackground(new java.awt.Color(153, 255, 153));
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Vantagens");
        jLabel28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        jLabel29.setBackground(new java.awt.Color(255, 153, 153));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Desvantagens");
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel2MousePressed(evt);
            }
        });

        list1.setBackground(new java.awt.Color(204, 255, 204));
        list1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        list2.setBackground(new java.awt.Color(255, 204, 204));
        list2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(list2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel28, jLabel29});

        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(list1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(list2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel28, jLabel29});

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Descrição");

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Clique em um item para exibir sua descrição.\nAtenção: Selecione Raça e Classe, gaste todos os pontos e digite um Nome para prosseguir.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton1.setText("Cancelar");
        jButton1.setBorder(null);
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton2.setText("Terminei");
        jButton2.setBorder(null);
        jButton2.setOpaque(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton25.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton25.setText("Começar");
        jButton25.setEnabled(false);
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        getAccessibleContext().setAccessibleDescription("+");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (Integer.parseInt(jLabel37.getText()) > 0 || textField1.getText() == "" || jComboBox1.getSelectedIndex() == 0 || jComboBox2.getSelectedIndex() == 0) {
            jTextArea1.setText("Você ainda não gastou todos os seus pontos, selecionou uma raça, ou classe ou colocou seu nome! Por favor use os pontos restantes ou insira um nome.");
        } else {
            if (Integer.parseInt(jLabel9.getText()) > 0) {
                jLabel24.setText("PVs: " + Integer.toString(Integer.parseInt(jLabel9.getText()) * classes.get(jComboBox2.getSelectedIndex() - 1).getPvs()));
                jLabel31.setText("PMs: " + Integer.toString(Integer.parseInt(jLabel9.getText()) * classes.get(jComboBox2.getSelectedIndex() - 1).getPms()));
            } else {
                jLabel24.setText("PVs: 1");
                jLabel31.setText("PMs: 1");
            }
            travarControles(false);
            jButton25.setEnabled(true);
            jTextArea1.setText("ATENÇÃO! Confira todos os dados. Se tiver certeza pressione Começar.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jButton25.isEnabled()) {
            jButton25.setEnabled(false);
            travarControles(true);
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void jLabel2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MousePressed
        JLabel sender = (JLabel) evt.getSource();
        switch (sender.getAccessibleContext().getAccessibleName()) {
            case ("Força"):
                jTextArea1.setText("FORÇA\nForça é sua capacidade de levantar peso, empurrar e puxar coisas, e causar dano com socos,"
                        + "chutes e golpes com armas. Quanto maior sua Força, maior o dano que você provoca em combate corporal ou seja,"
                        + " ataques contra alvos que estejam ao alcance de suas mãos ou pés. A Força será somada à sua Habilidade para "
                        + "determinar sua Força de Ataque quando você luta corpoacorpo. Um personagem também pode usar sua Força para "
                        + "fazer coisas que envolvem grande força física, como derrubar portas, empurrar carros, carregar peso...\n Muitas "
                        + "vezes, contudo, a Força não significa exatamente força bruta e sim o dano que você (ou suas armas) provocam. \n"
                        + "F0. Média para pessoas normais. Com toda a sua força, você pode levantar até 100 kg.\n "
                        + "F1. A força de um gorila; você pode levantar até 350 kg.\n"
                        + "F2. Você é MUITO mais forte que um gorila! Pode levantar até 900 kg.\n"
                        + "F3. Pode levantar até 2.000 quilos (um carro pequeno).\n"
                        + "F4. Pode levantar até 5.000 quilos (um carro grande ou dois pequenos).\n"
                        + "F5. Pode levantar até 10.000 quilos (uma porção de carros).\n");
                break;
            case ("Habilidade"):
                jTextArea1.setText("HABILIDADE\nA mais importante das Características não recomendamos que nenhum personagem jogador tenha menos de"
                        + " 2 pontos nela. Habilidade corresponde à agilidade, velocidade, equilíbrio e, em certo nível, à inteligência"
                        + " do personagem.\n Você usa a Habilidade para atos impressionantes, corajosos e sobrehumanos, como lutar, saltar"
                        + " de prédios, agarrar helicópteros em pleno ar e coisas assim. Um personagem com Habilidade 0 é uma pessoa"
                        + " comum, sem nenhum treinamento atlético ou em combate: para ele, é muito difícil (ou mesmo impossível) lutar"
                        + " ou realizar grandes façanhas atléticas.\n A Habilidade é somada à Força para descobrir a Força de Ataque em"
                        + " ataques corporais, ou somada ao Poder de Fogo para ataques à distância. A Habilidade também é somada à"
                        + " Armadura para calcular sua Força de Defesa. Muitas vezes"
                        + ", quando um personagem precisa usar algum conhecimento (como Perícias), será exigido dele um teste de Habilidade. \n"
                        + "H0. Média para pessoas normais. \n"
                        + "H1. A agilidade e velocidade de um ginasta olímpico. \n"
                        + "H2. Você pode fazer malabarismos com cinco facas e olhos vendados. \n"
                        + "H3. Você pode correr pelas paredes, e saltar tão alto e tão longe que quase pode voar. \n"
                        + "H4. Você pode atravessar a cidade saltando sobre prédios. \n"
                        + "H5. Você é difícil de vencer em combate; raramente falha ao realizar um ataque ou esquiva.");
                break;
            case ("Resistência"):
                jTextArea1.setText("RESISTÊNCIA\nEsta é a constituição, o vigor físico do personagem. Quanto maior sua Resistência, mais ferimentos você pode sofrer antes de "
                        + "morrer. Uma Resistência elevada também ajuda um personagem a tolerar venenos, doenças e outros agentes nocivos à saúde.\nEmbora esteja mais"
                        + " ligada à vitalidade do corpo, a Resístêncía também mede sua determinação, força de vontade e poder mental. Quando o personagem é alvo de"
                        + " uma magia ou poder psíquico, ter uma alta Resistência reduz suas chances de sofrer um efeito nocivo: quanto mais alta a Resistência, mais"
                        + " difícil será afetálo com magia ou outro poder sobrenatural.\n Pontos de Vida e de Magia: a Resistência também determina diretamente seus "
                        + "Pontos de Vida e Pontos de Magia. Quanto maior ela for, mais PVs e PMs você terá.\n"
                        + "R0. Média para pessoas normais. 1 PV e 1 PM.\n"
                        + "R1. Você tem uma saúde perfeita. 2 a 6 PVs e PMs.\n"
                        + "R2. Você sobrevive à maioria dos ferimentos que matariam um homem normal. 4 a 12 PVs e PMs.\n"
                        + "R3. Você é um daqueles artistas marciais que leva horas para cair. 6 a 18 PVs e PMs.\n"
                        + "R4. Carro blindado. 8 a 24 PVs e PMs.\n"
                        + "R5. Tanque de guerra. 10 a 30 PVs e PMs.");
                break;
            case ("Armadura"):
                jTextArea1.setText("ARMADURA\nEsta Característica representa a proteção corporal do personagem. Apesar do nome, não precisa ser uma 'armadura' no sentido"
                        + " literal; pode ser seu próprio couro ou carapaça, um escudo, campo de força, ou apenas habilidade de bloqueio.\n Então, é possível que"
                        + " uma aparentemente frágil garotinha tenha um poderoso escudo energético que vale como uma Armadura 5!\n Quando um personagem recebe um"
                        + " ataque, ele soma sua Habilidade à Armadura para determinar sua Força de Defesa. Em certos casos, caso esteja surpreso ou incapaz de"
                        + " se mover, apenas a Armadura será válida para sua FD. \n"
                        + " A0. Proteção nenhuma. Se não puder contar com sua Habilidade, qualquer dano que você receber vai feri-lo.\n"
                        + "A1. Boa proteção. Você consegue se proteger de ataques pouco poderosos.\n"
                        + "A2. Colete à prova de balas.\n"
                        + "A3. Armadura robótica pesada.\n"
                        + "A4. Carro blindado.\n"
                        + "A5. Tanque de guerra.\n");
                break;
            case ("Poder de Fogo"):
                jTextArea1.setText("PODER DE FOGO\n Quando o alvo está além do alcance dos socos e chutes, o personagem só pode contar com ataques à distância."
                        + " Essa capacidade é representada pelo Poder de Fogo. Como acontece com a Força, o PdF será somado à sua Habilidade para determinar sua"
                        + " Força de Ataque quando você faz um ataque à distância.\nLimite de Munição: normalmente, a munição"
                        + " de um personagem nunca se esgota – ele pode usar o Poder de Fogo sempre que quiser.\n Alcance: normalmente, PdF"
                        + " pode ser usado para atingir qualquer coisa que esteja à vista, não importa quão distante esteja."
                        + "PdF0. Nenhum Poder de Fogo. Você não pode fazer ataques à longa distância."
                        + "PdF1. Equivale a um revólver ou outra arma leve. "
                        + "PdF2. Metralhadora."
                        + "PdF3. Bazuca."
                        + "PdF4. Bateria antiaérea."
                        + "PdF5. Míssil antitanque.");
                break;
            case ("Água"):
                jTextArea1.setText("Água: além de criar e controlar água, uma vez que o corpo humano é formado por 70% de água, este Caminho também está ligado à cura, resistência e transmutação do corpo.");
                break;
            case ("Terra"):
                jTextArea1.setText("Terra: a força bruta, o terremoto, a destruição. O mago da terra tem poder sobre o solo e as pedras, e também sobre a duração, determinação, resistência, força de vontade e a passagem do tempo.");
                break;
            case ("Fogo"):
                jTextArea1.setText("Fogo: o mais destruidor elemento. O Caminho do Fogo é o mais tentador e, ao mesmo tempo, o mais perigoso.\n Com suas magias o mago pode criar, controlar e moldar as chamas, assim como resistir ao fogo e calor.");
                break;
            case ("Ar"):
                jTextArea1.setText("Ar: um dos mais destrutivos elementos da natureza, responsável pelos tornados e furacões, e também coisas sutis como odores. Como o som é provocado por vibração no ar, este Caminho também é responsável pelas magias sonoras.");
                break;
            case ("Luz"):
                jTextArea1.setText("Luz: este é o caminho da luz e da ilusão. Tudo o que vemos não passa de luz refletida; portanto, o mago da luz tem poder sobre aquilo que as pessoas vêem... ou não vêem. Mas cuidado, esta magia também pode ser destrutiva ela lida com os raios laser! O Caminho da Luz também trata da mágica purificadora, ligada ao bem.");
                break;
            case ("Trevas"):
                jTextArea1.setText("Trevas: o Caminho das sombras, escuridão, morte e destruição. Esta magia é mais utilizada por vilões como vampiros, demônios e elfos negros; heróis raramente seguem o Caminho das Trevas, pois este é o caminho ligado ao mal e decadência.");
                break;
            case ("Nome"):
                jTextArea1.setText("Aqui voce escolhe o nome do seu personagem que você vai acompanhar pelo resto da aventura");
                break;
            case ("Atributos"):
                jTextArea1.setText("");
                break;
            case ("Caminhos da Magia"):
                jTextArea1.setText("Caminhos e Focus\nA magia está dividida em seis Caminhos Elementais: Fogo, Terra, Água, Ar, Luz e Trevas. "
                        + "Um mago pode se especializar em apenas um Caminho, ou então em vários deles.\n Quando construir seu personagem mago, você pode escolher "
                        + "quantos Caminhos quiser. Cada Caminho tem um valor que vai de 1 a 5, como se fosse uma das Características básicas (F, H, R, A, PdF)."
                        + " Esse valor será seu Focus: se você tem Fogo 3, por exemplo, seu Focus no Caminho do Fogo será 3.\n Cada ponto de Focus, em qualquer"
                        + " Caminho, custa 1 ponto normal de personagem.\n Como acontece com as Características, nenhum personagem jogador recém criado pode ter "
                        + "Focus acima de 5.");
                break;
            case ("Vantagens"):
                jTextArea1.setText("Vantagens e Desvantagens são diversos poderes ou problemas extras que um personagem pode ter. Enquanto as Vantagens são qualidades e poderes"
                        + " extras que um personagem tem, as Desvantagens são coisas ruins, que atrapalham sua vida.\n Estas vantagens são usadas para ter acesso á"
                        + " alguns movimentos e magias durante o jogo, além de aluns beneficios durante a história. Por outro lado as desvantagens servem para"
                        + " atrapalhar o personagem durante sua aventura.\n Vantagens e desvantagens são obtidas na escolha de Raça e Classe e tabém durante o jogo, cada raça tem vantagens e"
                        + "desvantagens com relação as outras, assim como as classes.");
                break;
            case ("Desvantagens"):
                jTextArea1.setText("Vantagens e Desvantagens são diversos poderes ou problemas extras que um personagem pode ter. Enquanto as Vantagens são qualidades e poderes"
                        + " extras que um personagem tem, as Desvantagens são coisas ruins, que atrapalham sua vida.\n Estas vantagens são usadas para ter acesso á"
                        + " alguns movimentos e magias durante o jogo, além de aluns beneficios durante a história. Por outro lado as desvantagens servem para"
                        + " atrapalhar o personagem durante sua aventura.\n Vantagens e desvantagens são obtidas na escolha de Raça e Classe e tabém durante o jogo, cada raça tem vantagens e"
                        + "desvantagens com relação as outras, assim como as classes.");
                break;
        }
    }//GEN-LAST:event_jLabel2MousePressed

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered
//        notification.setLocation(getMousePosition());
//        notification.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
//        notification.setVisible(true);
    }//GEN-LAST:event_jLabel2MouseExited

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        JComboBox sender = (JComboBox) evt.getSource();
        switch (sender.getAccessibleContext().getAccessibleName()) {
            case ("Raça"):
                if (jComboBox1.getSelectedIndex() > 0) {
                    zerarValores();
                    getClasses(racas.get(jComboBox1.getSelectedIndex() - 1));
                    setarValoresRaciais(racas.get(jComboBox1.getSelectedIndex() - 1));
                    jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - (racas.get(jComboBox1.getSelectedIndex() - 1).getCusto())));
                    jTextArea1.setText(racas.get(jComboBox1.getSelectedIndex() - 1).getDescricao());

                } else {
                    zerarValores();
                    getClasses();
                    jTextArea1.setText("Escolha sua raça na lista, algumas raças custam pontos e, dependendo da raça limita as classes que voce pode escolher");
                }
                break;


            case ("Classe"):
                //   //System.out.println(jComboBox2.getSelectedIndex());
                if (jComboBox2.getSelectedIndex() > 0) {
                    zerarValores();
                    setarValoresRaciais(racas.get(jComboBox1.getSelectedIndex() - 1));
                    setarValoresdeClasse(classes.get(jComboBox2.getSelectedIndex() - 1));
                    jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - (racas.get(jComboBox1.getSelectedIndex() - 1).getCusto())));
                    jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - (classes.get(jComboBox2.getSelectedIndex() - 1).getCusto())));
                    jTextArea1.setText(classes.get(jComboBox2.getSelectedIndex() - 1).getDescricao());
                } else {
                    jTextArea1.setText("Escolha sua Classe na lista, algumas classes custam pontos e, dependendo da raça é limita o número classes que voce pode escolher");
                }
                break;
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButtonPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlusActionPerformed
        JButton sender = (JButton) evt.getSource();
        switch (sender.getAccessibleContext().getAccessibleName()) {
            case ("Força"):
                if (Integer.parseInt(jLabel7.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel7.setText(Integer.toString(Integer.parseInt(jLabel7.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Habilidade"):
                if (Integer.parseInt(jLabel8.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel8.setText(Integer.toString(Integer.parseInt(jLabel8.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Resistência"):
                if (Integer.parseInt(jLabel9.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel9.setText(Integer.toString(Integer.parseInt(jLabel9.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Armadura"):
                if (Integer.parseInt(jLabel10.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel10.setText(Integer.toString(Integer.parseInt(jLabel10.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Poder de Fogo"):
                if (Integer.parseInt(jLabel11.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel11.setText(Integer.toString(Integer.parseInt(jLabel11.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Água"):
                if (Integer.parseInt(jLabel32.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel32.setText(Integer.toString(Integer.parseInt(jLabel32.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Terra"):
                if (Integer.parseInt(jLabel33.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel33.setText(Integer.toString(Integer.parseInt(jLabel33.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Fogo"):
                if (Integer.parseInt(jLabel34.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel34.setText(Integer.toString(Integer.parseInt(jLabel34.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Ar"):
                if (Integer.parseInt(jLabel35.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel35.setText(Integer.toString(Integer.parseInt(jLabel35.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Luz"):
                if (Integer.parseInt(jLabel36.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel36.setText(Integer.toString(Integer.parseInt(jLabel36.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Trevas"):
                if (Integer.parseInt(jLabel44.getText()) >= 5) {
                    jTextArea1.setText("Limite de pontos nesta caracteristicas atingido!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) > 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) - 1));
                        jLabel44.setText(Integer.toString(Integer.parseInt(jLabel44.getText()) + 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
        }
    }//GEN-LAST:event_jButtonPlusActionPerformed

    private void jButtonMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMinusActionPerformed
        JButton sender = (JButton) evt.getSource();
        switch (sender.getAccessibleContext().getAccessibleName()) {
            case ("Força"):
                if (Integer.parseInt(jLabel7.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel7.setText(Integer.toString(Integer.parseInt(jLabel7.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Habilidade"):
                if (Integer.parseInt(jLabel8.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel8.setText(Integer.toString(Integer.parseInt(jLabel8.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Resistência"):
                if (Integer.parseInt(jLabel9.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel9.setText(Integer.toString(Integer.parseInt(jLabel9.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Armadura"):
                if (Integer.parseInt(jLabel10.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel10.setText(Integer.toString(Integer.parseInt(jLabel10.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Poder de Fogo"):
                if (Integer.parseInt(jLabel11.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel11.setText(Integer.toString(Integer.parseInt(jLabel11.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Água"):
                if (Integer.parseInt(jLabel32.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel32.setText(Integer.toString(Integer.parseInt(jLabel32.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Terra"):
                if (Integer.parseInt(jLabel33.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel33.setText(Integer.toString(Integer.parseInt(jLabel33.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Fogo"):
                if (Integer.parseInt(jLabel34.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel34.setText(Integer.toString(Integer.parseInt(jLabel34.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Ar"):
                if (Integer.parseInt(jLabel35.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel35.setText(Integer.toString(Integer.parseInt(jLabel35.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Luz"):
                if (Integer.parseInt(jLabel36.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel36.setText(Integer.toString(Integer.parseInt(jLabel36.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
            case ("Trevas"):
                if (Integer.parseInt(jLabel44.getText()) < 1) {
                    jTextArea1.setText("Nenhum ponto nesta caracteristica!");
                } else {
                    if (Integer.parseInt(jLabel37.getText()) >= 0) {
                        jLabel37.setText(Integer.toString(Integer.parseInt(jLabel37.getText()) + 1));
                        jLabel44.setText(Integer.toString(Integer.parseInt(jLabel44.getText()) - 1));
                    } else {
                        jTextArea1.setText("Você já gastou todos os seus pontos!");
                    }
                }
                break;
        }
    }//GEN-LAST:event_jButtonMinusActionPerformed

    private void jComboBox2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox2PropertyChange
    }//GEN-LAST:event_jComboBox2PropertyChange

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        mf3d.setP(criarFicha());
        mf3d.setStart(true);
        player.stopBackMusic();
        this.dispose();
    }//GEN-LAST:event_jButton25ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;




                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CriacaodoPersonagem.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CriacaodoPersonagem.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CriacaodoPersonagem.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CriacaodoPersonagem.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CriacaodoPersonagem().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private java.awt.List list1;
    private java.awt.List list2;
    private java.awt.TextField textField1;
    // End of variables declaration//GEN-END:variables
}
