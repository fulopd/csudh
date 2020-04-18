package csudh;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static List<Domain> domainLista() {
		List<Domain> domainLista = new ArrayList<>();
		try {
			List<String> beolvas = Files.readAllLines(Paths.get("csud.txt"));
			for (String sor : beolvas.subList(1, beolvas.size())) {
				String[] split = sor.split(";");
				Domain o = new Domain(split[0], split[1]);
				
				domainLista.add(o);
			}
		} catch (IOException e) {
			System.err.println("Hiba a beolvasasnal");
		}
		return domainLista;
	}
	
	public static void domain(String domain) {
		String[] split = domain.split("\\.");
		
		for (int i = 0; i < split.length; i++) {
			System.out.println("\t"+(i+1)+". szint " + split[i]);
		}
		if (split.length < 4 ) System.out.println("\t4.szint: nincs");
		if (split.length < 5 ) System.out.println("\t5.szint: nincs");
		
	}
	
	
	public static void main(String[] args) {

		List<Domain> domain = domainLista();
		System.out.println("3. feladat: Domainek száma: "+ domain.size());
		System.out.println("4. faladet: Az elsõ domin felépítése: ");
		domain(domain.get(0).getDomain());
		
		String html = "<table>\n"
                + "<tr>\n"
                + "<th style='text-align: left'>Ssz</th>\n"
                + "<th style='text-align: left'>Host domain neve</th>\n"
                + "<th style='text-align: left'>Host IP címe</th>\n"
                + "<th style='text-align: left'>1. szint</th>\n"
                + "<th style='text-align: left'>2. szint</th>\n"
                + "<th style='text-align: left'>3. szint</th>\n"
                + "<th style='text-align: left'>4. szint</th>\n"
                + "<th style='text-align: left'>5. szint</th>\n"
                + "</tr> ";
        
        for (int i = 0; i < domain.size(); i++) {
            String[] split = domain.get(i).getDomain().split("\\.");
            html += "<tr>\n"
                    + "<th style='text-align: left'>"+(i+1)+"</th>\n"
                    + "<td>"+domain.get(i).getDomain()+"</td>\n"
                    + "<td>"+domain.get(i).getIpcim()+"</td>\n"
                    + "<td>"+split[0]+"</td>\n"
                    + "<td>"+split[1]+"</td>\n"
                    + "<td>"+split[2]+"</td>\n"
                    + "<td>"+ ((split.length < 4) ? "nincs" : split[3] )+"</td>\n"
                    + "<td>"+ ((split.length < 5) ? "nincs" : split[4] )+"</td>\n"
                    + "</tr>\n";
        }

        html += "</table>";
        
				
        try {
			Files.write(Paths.get("table.html"), html.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
