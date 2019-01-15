package br.com.andrefilipe.relatorio;

import java.util.List;

import br.com.andrefilipe.model.Cliente;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ClienteREL {
	private String path; // Caminho base - Project Location
    private String pathToReportPackage; // Caminho para o package onde está armazenado o relatorio Jarper
    
    //Recupera os caminhos para que a classe possa encontrar os relatórios
    public ClienteREL() {
          this.path = this.getClass().getClassLoader().getResource("").getPath();
          this.pathToReportPackage = this.path + "br/com/andrefilipe/jasper/";
    }

    //Imprime/gera uma lista de Clientes
    public void imprimir(List<Cliente> clientes) throws Exception
    {
          JasperReport report = JasperCompileManager.compileReport(this.getPathToReportPackage() + "Clientes.jrxml");
          JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(clientes));
          JasperExportManager.exportReportToPdfFile(print, "C:/Relatorio_de_Clientes.pdf"); //Exportar PDF para determinado diretório
          JasperViewer.viewReport(print,false); //Ler PDF
    }

    public String getPathToReportPackage() {
          return this.pathToReportPackage;
    }

    public String getPath() {
          return this.path;
    }
}
