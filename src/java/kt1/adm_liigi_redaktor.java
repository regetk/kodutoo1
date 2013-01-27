package kt1;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell
 */
public class adm_liigi_redaktor extends HttpServlet {

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
            out.println("<title>Servlet adm_liigi_redaktor</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet adm_liigi_redaktor at " + request.getContextPath() + "</h1>");
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
        //veahaldus
        List<String> errors = getValidationErrors(request);
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            
            showForm(request, response);
            return;
        }
        //kontrollime, millist nuppu vajutati
        //Enumeration<String> paramNames = request.getParameterNames();
        //while (paramNames.hasMoreElements()) {
        //    String paramName = paramNames.nextElement();
        String kood = request.getParameter("ay_liik_kood");
        //if(kood==null){kood="";}
        String nimi = request.getParameter("ay_liik_nimi");
        String komm = request.getParameter("ay_liik_komm");
        String ylem_id = request.getParameter("ay_liik_ylemus");
        String alam_id = request.getParameter("ay_liik_alluv");
        
        String lisaListi = request.getParameter("lisa_alluv_listi");
        String saveLiik = request.getParameter("save_ay_liik");
        ArrayList lisatudList=new ArrayList();

        if (lisaListi!=null) {
            request.setAttribute("kood", kood);
            request.setAttribute("nimi", nimi);
            request.setAttribute("komm", komm);
            if(ylem_id.equals("0")) {
                request.setAttribute("ylem_id", null);
            }
                else {
                request.setAttribute("ylem_id", ylem_id);
            }
            if(alam_id.equals("0")) {
                request.setAttribute("alamMass", null);
            }
                else {
                lisatudList.add(alam_id);
            }
                request.setAttribute("alamMass", lisatudList);
            

        }

        if ((saveLiik!=null) && errors.isEmpty()) {

            //andmebaasi sisestatakse  korrektsed tähed
            request.setCharacterEncoding("UTF-8");
            //POSTist saadaolevad parameetrid.        


            Paring p = new Paring();
            ArrayList params = new ArrayList();
            params.add(kood);
            params.add(nimi);
            params.add(komm);

            String sql = "INSERT INTO RIIGI_ADMIN_YKSUSE_LIIK  VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',?,?,?,CURRENT_DATE,'9999-12-31')";
            p.DMLParing(sql, params);

            //lisame nyyd VOIMALIK_Alluvus
            int ylem_ID_nr = Integer.parseInt(ylem_id);

            if (ylem_ID_nr != 0) {
                //asume hankima viimati sisestatud ay liigi ID-d
                ArrayList param2 = new ArrayList();
                param2.add(kood);
                String sql2 = "SELECT riigi_admin_yksuse_lik_id  FROM RIIGI_ADMIN_YKSUSE_LIIK WHERE KOOD=?";
                Object res[][] = p.SelectParing(sql2, param2);
                String alluv_id = res[0][0].toString();


                ArrayList param3 = new ArrayList();
                param3.add(ylem_id);
                param3.add(alluv_id);
                String sql3 = "INSERT INTO VOIMALIK_ALLUVUS  VALUES (null,'An4', CURRENT_DATE,'Nobody','9999-12-31', NULL,'9999-12-31',?,?,'komm')";
                p.DMLParing(sql3, param3);
            }


            //System.out.println(request.getAttribute("kood"));
            
        }
    showForm(request, response);
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


        //ylemused

        Paring p = new Paring();
        String sql = "SELECT riigi_admin_yksuse_lik_id,nimetus FROM RIIGI_ADMIN_YKSUSE_LIIK";
        Object tulem[][] = p.SelectParing(sql, new ArrayList());


        //potentsiaalsed alluvad, kellel veel ylemust ei ole      

        String sql2 = "SELECT riigi_admin_yksuse_lik_id,NIMETUS FROM PUBLIC.RIIGI_ADMIN_YKSUSE_LIIK RIIGI_ADMIN_YKSUSE_LIIK WHERE RIIGI_ADMIN_YKSUSE_LIK_ID NOT IN (SELECT VOIMALIK_ALLUV_LIIK_ID FROM PUBLIC.VOIMALIK_ALLUVUS)";
        Object tulem2[][] = p.SelectParing(sql2, new ArrayList());


        request.setAttribute("vormiAndmed", tulem);
        request.setAttribute("allumatud", tulem2);
       
        request.getRequestDispatcher("adm_liigi_redaktor.jsp").forward(request, response);

    }

    //meetod andmete õigsuse kontrolliks ja teated, kui miskit on valesti
    private List<String> getValidationErrors(HttpServletRequest request) {
        List<String> errors = new ArrayList<String>();
        if ("".equals(request.getParameter("ay_liik_kood"))) {
            errors.add("Sisesta alluvusyksuse liigi kood!");
        }
        if ("".equals(request.getParameter("ay_liik_nimi"))) {
            errors.add("Sisesta alluvusyksuse liigi nimi!");
        }

        if ("".equals(request.getParameter("ay_liik_komm"))) {
            errors.add("Sisesta alluvusyksuse liigi kommentaar!");
        }
       
        return errors;
    }
}