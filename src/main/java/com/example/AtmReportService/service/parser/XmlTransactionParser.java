package com.example.AtmReportService.service.parser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.example.AtmReportService.model.Transaction;
import com.example.AtmReportService.service.parser.xmlhandlers.TransactionListWrapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class XmlTransactionParser implements TransactionParser {

    private static final String ATM_ID = "A";

    @Override
    public List<Transaction> parse(File file) throws IOException {
        try {
            JAXBContext context = JAXBContext.newInstance(TransactionListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            TransactionListWrapper wrapper = (TransactionListWrapper) unmarshaller.unmarshal(file);

            List<Transaction> transactions = wrapper.getTransactions();

            transactions.forEach(tx -> tx.setAtmId(ATM_ID));

            return transactions;
        } catch (JAXBException e) {
            throw new IOException("Error parsing XML", e);
        }
    }
}
