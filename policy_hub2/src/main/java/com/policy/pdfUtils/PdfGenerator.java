package com.policy.pdfUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.policy.dto.PdfResponseDto;
import com.policy.entity.Policy;
import com.policy.exceptionHandler.PdfGenerationException;

@Component
public class PdfGenerator {
	@Value("${policy.pdf.output-path}")
	private String outPutPath;
	
	public PdfResponseDto generatePdf(List<Policy> policies) throws IOException{

		Policy firstRecord = policies.get(0);
		SimpleDateFormat transactionDateFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss a");
		
		String policyNumberonFileName = firstRecord.getPolicyNumber();
		String currentDate = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format( new Date());
		String fileName = policyNumberonFileName+"_"+currentDate+".pdf";
		String filePath =outPutPath +fileName;
		
		Document document = new Document();
		
	try {
		PdfWriter.getInstance(document, new FileOutputStream(filePath));
		
		document.open();
		
		Font companytitleFont = new Font(Font.HELVETICA,20,Font.BOLD);
		Font titleFont = new Font(Font.HELVETICA,16,Font.BOLD);
		Font normalFont = new Font(Font.HELVETICA,12,Font.NORMAL);
		
		//adding logo
		Image companyLogo = Image.getInstance(getClass().getResource("/static/images/logo.png"));
		companyLogo.scaleToFit(120,80);
		companyLogo.setAlignment(Image.ALIGN_CENTER);
		document.add(companyLogo);
		//company name and details 
		Paragraph comapnyTitle = new Paragraph("PAVAN's NEW MODEL INSURANCE " , companytitleFont);
		comapnyTitle.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(comapnyTitle);
		Paragraph comapnydetails = new Paragraph(  "Insurance Services | Hyderabad, India\n" +
                "Email: support@pavaninsurance.com\n" +
                "Phone: +91-XXXXXXXXXX",  normalFont);
		comapnyTitle.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(comapnydetails);
		document.add(new Paragraph(" "));
		
		//Policy information. 
		Paragraph policyTitle = new Paragraph("POLICY INFORMATION FORM " , titleFont);
		policyTitle.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(policyTitle);
		document.add(new Paragraph(" "));
		document.add(new Paragraph("Policy Number : " +firstRecord.getPolicyNumber(), normalFont));
		document.add(new Paragraph("Policy Type: " +firstRecord.getPolicyType(), normalFont));
		document.add(new Paragraph("Policy State: " +firstRecord.getState(), normalFont));
		document.add(new Paragraph(" "));
		
		
		//Page-2 : Transactions history
		document.newPage();
		Paragraph transactionHistory = new Paragraph("TRANSACTION  DETAILS :  " , titleFont);
		transactionHistory.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(transactionHistory);
		document.add(new Paragraph(" "));
		
	    Table table = new Table(4);
	    table.setWidth(100);
	    table.setWidths(new float[] {10,25,20,45});
	    table.addCell(new Cell("ID"));
	    table.addCell(new Cell("TransactionType"));
	    table.addCell(new Cell("Premium"));
	    table.addCell(new Cell("CommitDate"));
		
	    for(Policy pol : policies) {
	    	table.addCell(new Cell(String.valueOf(pol.getPolicyId())));
	    	table.addCell(new Cell(String.valueOf(pol.getTransactionType())));
	    	table.addCell(new Cell(String.valueOf(pol.getPremium())));
	    	String commitDate = transactionDateFormat.format(pol.getTransactionCommitDate());
	    	table.addCell(new Cell(commitDate));
	    }
	    
	    document.add(table);
	    document.add(new Paragraph(" "));
	    
	    //last page 
	    document.newPage();
	    Paragraph acknowledgeStatementTitle = new Paragraph("ACKNOWLEDGEMENT  " , titleFont);
	    acknowledgeStatementTitle.setAlignment(Paragraph.ALIGN_CENTER);
	    document.add(acknowledgeStatementTitle);
	    document.add(new Paragraph(" "));
	    
	    Paragraph acknowledgeStatement = new Paragraph(" We acknowledge that the above policy transaction information has been generated "
	    		+ "based on the policy records available in our system." ,normalFont );
	    document.add(acknowledgeStatement);
	    document.add(new Paragraph(" "));
	    document.add(new Paragraph(" "));
	    
	    String generatedDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
	    String geneatedTime = new SimpleDateFormat("HH-mm-ss a").format(new Date());
	    document.add( new Paragraph("Form Generated Date : " +generatedDate ,normalFont));
	    document.add( new Paragraph("Form Generated Time : " +geneatedTime ,normalFont));
	    document.add(new Paragraph(" "));
		Paragraph signature = new Paragraph("Authorized signature" , normalFont);
		signature.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(signature);

		Paragraph signatureTitlebar = new Paragraph("-----------------------" , normalFont);
		signatureTitlebar.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(signatureTitlebar);
	
		Paragraph signatureTitle = new Paragraph("PAVAN's NEW MODEL INSURANCE" , normalFont);
		signatureTitle.setAlignment(Paragraph.ALIGN_RIGHT);
		document.add(signatureTitle);
		
		document.close();
		
		PdfResponseDto pdfResponseDto = new PdfResponseDto();
		pdfResponseDto.setFileName(fileName);
		pdfResponseDto.setFilePath(filePath);
		pdfResponseDto.setMessage("File Generated Successfully");
		
		return pdfResponseDto;
	}
	catch(DocumentException | FileNotFoundException e ) {
		 throw new PdfGenerationException("Unable to generate the pdf for policy " +firstRecord.getPolicyNumber(),e);
	}
		
	}
}
