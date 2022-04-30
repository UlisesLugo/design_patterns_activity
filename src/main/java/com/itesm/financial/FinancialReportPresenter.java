package com.itesm.financial;

import java.io.IOException;
import java.util.List;

// Interface for Open/Closed principle
public interface FinancialReportPresenter {

    public void createContent();
    public void createFile() throws IOException;

}
