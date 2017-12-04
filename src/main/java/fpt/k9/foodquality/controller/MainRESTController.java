package fpt.k9.foodquality.controller;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import fpt.k9.foodquality.dao.Email;
import fpt.k9.foodquality.dao.FeedbackDAO;
import fpt.k9.foodquality.dao.HistoryDAO;
import fpt.k9.foodquality.dao.ProducerDAO;
import fpt.k9.foodquality.dao.ProductDAO;
import fpt.k9.foodquality.dao.QrDAO;
import fpt.k9.foodquality.dao.RateDAO;
import fpt.k9.foodquality.dao.RegisterPTS_DAO;
import fpt.k9.foodquality.dao.RegisterProduct_DAO;
import fpt.k9.foodquality.dao.ReportDAO;
import fpt.k9.foodquality.dao.SellerDAO;
import fpt.k9.foodquality.dao.TransporterDAO;
import fpt.k9.foodquality.dao.UserDAO;
import fpt.k9.foodquality.model.Feedback;
import fpt.k9.foodquality.model.History;
import fpt.k9.foodquality.model.Product;
import fpt.k9.foodquality.model.Rate;
import fpt.k9.foodquality.model.RegisterPTS;
import fpt.k9.foodquality.model.RegisterProduct;
import fpt.k9.foodquality.model.Report;
import fpt.k9.foodquality.model.Seller;
import fpt.k9.foodquality.model.Transporter;
import fpt.k9.foodquality.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRESTController {

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private FeedbackDAO fbDAO;
	@Autowired
	private RateDAO rDAO;
	@Autowired
	private QrDAO qrDAO;
	@Autowired
	private Email e;
	@Autowired
	private HistoryDAO hDAO;
	@Autowired
	private RegisterPTS_DAO RegisterDAO;
	@Autowired
	private RegisterProduct_DAO RegisterPDAO;
	@Autowired
	private TransporterDAO tDAO;
	@Autowired
	private ProducerDAO pDAO;
	@Autowired
	private SellerDAO sDAO;
	@Autowired
	private ReportDAO reportDAO;

	@RequestMapping("/")
	public String welcome() {
		return "Welcome to RestTemplate Example.";
	}

	@RequestMapping(value = "/search/{findw}", //
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Product> searchProducts(@PathVariable("findw") String findw) {
		return productDAO.searchProducts(findw);
	}

	@RequestMapping(value = "/register", //
	method = RequestMethod.POST, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String registerUser(@RequestBody User nuser,
			HttpServletResponse response) {
		String result = userDAO.registerUser(nuser);
		if (result != "Register success")
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		return result;
	}

	@RequestMapping(value = "/login/{uacc}/{upass}", //
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Object> getUserbyusn(@PathVariable("uacc") String usn,
			@PathVariable("upass") String password, HttpServletResponse response) {
		List<Object> result = userDAO.getUserbyusn(usn, password);
		if (result == null) {
			response.setStatus(HttpServletResponse.SC_EXPECTATION_FAILED);
		}
		return result;
	}

	@RequestMapping(value = "/feedback", //
	method = RequestMethod.POST, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Boolean addFeedback(@RequestBody Feedback fb) {
		return fbDAO.addFeedback(fb);
	}

	@RequestMapping(value = "/feedback/{pid}",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Feedback> getFeedbackbyPID(@PathVariable("pid") String pid) {
		return fbDAO.getFeedbackbyPID(pid);
	}

	@RequestMapping(value = "/rating", //
	method = RequestMethod.POST, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public Boolean addRate(@RequestBody Rate mrate) {
		return rDAO.addRate(mrate);
	}

	@RequestMapping(value = "/qrcode/{qr}",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Object> getQR(@PathVariable("qr") String qr) {
		return qrDAO.getQR(qr);
	}

	@RequestMapping(value = "/changepwd", //
	method = RequestMethod.POST, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String changepwd(@RequestBody User user, HttpServletResponse response) {
		String result = userDAO.changpwd(user);
		if (result != "Change pwd success")
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return result;
	}

	@RequestMapping(value = "/forgotpwd",//
	method = RequestMethod.POST, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String forgotpwd(@RequestBody User user, HttpServletResponse response) {
		user = userDAO.getUserbyName(user.getUacc());
		String result = e.sendEmail(user);
		if (result != "Done!")
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return result;
	}

	@RequestMapping(value = "/editprofile",//
	method = RequestMethod.POST, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String editprofile(@RequestBody User user,
			HttpServletResponse response) {
		String result = "OK";
		if (user.getUmail() != "" && user.getUmail() != null) {
			result = userDAO.checkMail(user.getUmail(),user.getUid());
		}
		if (result.equalsIgnoreCase("OK"))
			result = userDAO.updateProfile(user);
		if (result != "Update success")
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return result;
	}

	@RequestMapping(value = "/rate/{pid}/{uid}",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Float> getRate(@PathVariable("pid") String pid,@PathVariable("uid") String uid) {
		return rDAO.getAVGRate(pid,uid);
	}

	@RequestMapping(value = "/history/{uid}",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String getHistory(@PathVariable("uid") String uid) {
		return "[" + hDAO.getHistorybyUID(uid).getHcontent() + "]";
	}

	@RequestMapping(value = "/history/{uid}/{pid}",//
	method = RequestMethod.POST, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String addHistory(@PathVariable("uid") String uid,
			@PathVariable("pid") String pid) {
		String result = "OK";
		History newh = new History();
		// product information
		Product product = productDAO.getProductbyID(pid);
		// history date
		Calendar calendar = Calendar.getInstance();
		java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime()
				.getTime());
		// set new history
		newh.setHuid(uid);
		newh.setHcontent("{\"pid\":\"" + pid + "\", \"pname\":\""
				+ product.getPname() + "\", \"pimage\":\""
				+ product.getPimage() + "\", \"pprice\":" + product.getPprice()
				+ ",\"date\":\"" + ourJavaDateObject + "\"}");
		result = hDAO.addHistory(newh);
		return result;
	}

	@RequestMapping(value = "/registerPTS",//
	method = RequestMethod.POST, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String registerPTS(@RequestBody RegisterPTS record,
			HttpServletResponse response) {
		String result = "Sucess";
		result = RegisterDAO.checkUser(record.getRegisterUid());
		if (result.equalsIgnoreCase("OK"))
			result = RegisterDAO.checkTax(record.getRegisterTax());
		if (result.equalsIgnoreCase("OK"))
			result = RegisterDAO.addRegister(record);
		if (result != "Success")
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return result;
	}

	@RequestMapping(value = "/registerAll",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<RegisterPTS> getAllRegister() {
		return RegisterDAO.getAllRegister();
	}

	@RequestMapping(value = "/registerPTS/{type}",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<RegisterPTS> getRegisterbyType(@PathVariable("type") int type) {
		return RegisterDAO.getRegisterbyType(type);
	}

	@RequestMapping(value = "/registerPTS_ID/{id}",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public RegisterPTS getRegisterbyID(@PathVariable("id") String id) {
		return RegisterDAO.getRegisterbyID(id);
	}

	@RequestMapping(value = "/approvePTS/{reid}",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String approvePTS(@PathVariable("reid") String RegisterID) {
		return RegisterDAO.ApproveRegister(RegisterID);
	}

	@RequestMapping(value = "/registerProduct",//
	method = RequestMethod.POST, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String registerProduct(@RequestBody RegisterProduct record,
			HttpServletResponse response) {
		String result = "Sucess";
		result = RegisterPDAO.addRegister(record);
		if (result != "Success")
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		return result;
	}

	@RequestMapping(value = "/getAllRegisterProduct",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<RegisterProduct> getAllRegisterProduct() {
		return RegisterPDAO.getAllRegister();
	}

	@RequestMapping(value = "/registerProduct_ID/{id}",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public RegisterProduct getRegisterProduct(@PathVariable("id") String id) {
		return RegisterPDAO.getRegisterbyID(id);
	}

	@RequestMapping(value = "/approveProduct/{id}",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String approveProduct(@PathVariable("id") String ID) {
		return RegisterPDAO.ApproveProduct(ID);
	}

	@RequestMapping(value = "/getAllTransporter",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Transporter> getAllTransporter() {
		return tDAO.getAllTrans();
	}

	@RequestMapping(value = "/getAllSeller",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Seller> getAllSeller() {
		return sDAO.getAllSeller();
	}

	@RequestMapping(value = "/report", //
	method = RequestMethod.POST, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String addRate(@RequestBody Report mreport) {
		return reportDAO.addReport(mreport);
	}
	
	@RequestMapping(value = "/getProductbyRefID/{mtype}/{refID}",//
	method = RequestMethod.GET, //
	produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public List<Product> getProductbyRefID(@PathVariable("mtype") String mtype,@PathVariable("refID") String refID) {
		return productDAO.getProductsbyRefID(mtype, refID);
	}
}