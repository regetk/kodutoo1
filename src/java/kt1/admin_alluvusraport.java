package kt1;


import kt1.TableRow;
import kt1.Paring;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author reget.kalamees
 */
public class admin_alluvusraport extends HttpServlet {

    @Override
    public void init() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet admin_alluvusraport</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet admin_alluvusraport at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showForm(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //GET parameetrite lugemine
        String kuupaev = request.getParameter("kuupaev");
        String ayLiik = request.getParameter("ayLiik");

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dKp = new Date();
        //kui pole määratud pannakse praegune kuupäev ja valitakse listist esimene
        if (kuupaev == null) {
            kuupaev = dateFormat.format(dKp);
        }
        if (ayLiik == null) {
            ayLiik = "1";
        }
        int iAyLiik = Integer.parseInt(ayLiik);
        //saada parameetrid JSP lehele tagasi kah
        request.setAttribute("kuupaev", kuupaev);
        request.setAttribute("vYksus", ayLiik);

        //üksuste liikide päring
        Paring p = new Paring();
        String sql = "SELECT riigi_admin_yksuse_lik_id,nimetus FROM RIIGI_ADMIN_YKSUSE_LIIK";
        Object tulem[][] = p.SelectParing(sql, new ArrayList());
        //SQL alluvus
        request.setAttribute("yLiik", tulem);

        //üksuste nimede päring
        ArrayList al = new ArrayList();
        al.add(iAyLiik);
        sql = "select riigi_admin_yksus_id,nimetus from riigi_admin_yksus where riigi_admin_yksuse_lik_id=?";
        Paring pYksus = new Paring();
        Object ayd[][] = pYksus.SelectParing(sql, al);
        request.setAttribute("yksused", ayd);

        if (ayd != null) {
            //kõik üksuse alluvad
            for (int a = 0; a < ayd.length; a++) {
                //võta id ja tee sellega päring, mis korjab välja alluvad
                ArrayList alLeitudYksus = new ArrayList();
                alLeitudYksus.add(ayd[a][0]);
                //üksuste alluvate päring
                sql = "select riigi_admin_yksus_id,nimetus from RIIGI_ADMIN_YKSUS Where( riigi_admin_yksus_id  in (select alluv_yksus_id  from ADMIN_ALLUVUS where ylemus_yksus_ID=?))";
                //need, kelle ülem üksus id on iAyLiik admin alluvuse tabelist 
                Object alluvad[][] = p.SelectParing(sql, alLeitudYksus);
                ArrayList<TableRow> alTabList = new ArrayList<TableRow>();
                //tee alluvatest TableRow objektid
                if (alluvad != null) {
                    alTabList.clear();
                    //tehakse tabeli read
                    for (int b = 0; b < alluvad.length; b++) {
                        TableRow tRow = new TableRow(alluvad[b][0].toString(), alluvad[b][1].toString());
                        alTabList.add(tRow);
                    }

                }//if(alluvad!=null) 
                //lisa artribuut
               
                request.setAttribute("rowsKey"+a, alTabList);


            }//kõik kindlat tüüpi üksused
        }//if admin_ykssusel on alluvad

        request.getRequestDispatcher("admin_alluvusraport.jsp").forward(request, response);

    }
}
