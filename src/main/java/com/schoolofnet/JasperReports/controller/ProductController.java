package com.schoolofnet.JasperReports.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private DataSource dataSource;
	
	@GetMapping("/report")
	public void printReport(HttpServletResponse response) throws JRException, IOException, SQLException {
		
		InputStream reportStream = this.getClass().getResourceAsStream("/reports/Products.jasper");
		Map<String, Object> params = new HashMap<>();
		
		params.put("PARAM_1", "CUSTOM PARAM");
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());
		
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", "inline; filename=products_son.pdf");
		
		OutputStream outputStream = response.getOutputStream();
		
		JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
	}
	
}
