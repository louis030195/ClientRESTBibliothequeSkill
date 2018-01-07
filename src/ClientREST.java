import java.net.URI;
import java.util.Scanner;

import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class ClientREST {
	public static void main(String[] args) throws Exception {
		//on utilise une fabrique pour créer le client
		Client client = Client.create(new DefaultClientConfig());
		//on cré URI pour pouvoir interagir en utilisant l’objet client en http
		URI uri=UriBuilder.fromUri("http://localhost:8080/BibliothequeSkill").build();

		ClientResponse response;
		String corpsRepHttp;


		String choice;
		boolean finished = false;
		
		String table = "";
		String jsonString = "";
		Scanner sc = new Scanner(System.in);


		while(!finished)
		{
			boolean finishedSubMenu = false;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~BibliothequeSkill~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			System.out.println("Que souhaitez vous faire ?");

			System.out.println("1 - Gestion des auteurs");
			System.out.println("2 - Gestion des livres");
			System.out.println("3 - Gestion des utilisateurs");
			System.out.println("4 - Gestion des prets");
			System.out.println("5 - Gestion des categories");

			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");



			choice = sc.nextLine();

			switch(choice){
			case "1":
				table = "auteur";
				break;
			case "2":
				table = "livre";
				break;
			case "3":
				table = "utilisateur";
				break;
			case "4":
				table = "pret";
				break;
			case "5":
				table = "categorie";
				break;
			default:
				System.out.println("Veuillez choisir parmi la liste de choix");
				break;

			}



			while(!finishedSubMenu) {
				

				System.out.println("1 - Ajouter");
				System.out.println("2 - Tout afficher");
				System.out.println("3 - Mettre à jour");
				System.out.println("4 - Supprimer");
				System.out.println("5 - Retour menu principal");

				choice = sc.nextLine();
				
				switch(choice) {
				case "1":
					jsonString = "{\"id\":";
					System.out.println("id ? ");
					jsonString += Integer.parseInt(sc.nextLine());
					switch(table) {
						case "auteur":
							System.out.println("à propos de ?");
							jsonString += ",\"aProposDe\":"+sc.nextLine();
							System.out.println("Nationalité ?");
							jsonString += ",\"nationalite\":"+sc.nextLine();
							System.out.println("Type d'auteur : PRINCIPAL ou COAUTEUR ?");
							jsonString += ",\"type\":"+sc.nextLine();
							break;
						case "livre":
							System.out.println("Titre ?");
							jsonString += ",\"titre\":"+sc.nextLine();
							System.out.println("Résumé ?");
							jsonString += ",\"resume\":"+sc.nextLine();
							System.out.println("Quantité ?");
							jsonString += ",\"quantite\":"+Integer.parseInt(sc.nextLine());
							System.out.println("ISBN ?");
							jsonString += ",\"isbn\":"+Integer.parseInt(sc.nextLine());
							System.out.println("Photo ?");
							jsonString += ",\"photo\":"+sc.nextLine();
							System.out.println("Date de publication ?");
							jsonString += ",\"datePublication\":"+sc.nextLine();
							System.out.println("Auteur ?");
							jsonString += ",\"auteur\":"+Integer.parseInt(sc.nextLine());
							System.out.println("Categorie ?");
							jsonString += ",\"categorie\":"+Integer.parseInt(sc.nextLine());
							// pas de prêts de base
							jsonString += ",\"pret\":NULL";
							break;
						case "utilisateur":
							System.out.println("Nom ?");
							jsonString += ",\"nomUtilisateur\":"+sc.nextLine();
							System.out.println("Mot de passe ?");
							jsonString += ",\"motDePasse\":"+sc.nextLine();
							// Compte actif de base
							jsonString += ",\"statutCompte\":ACTIF";
							// Derniere connexion null de base
							jsonString += ",\"derniereConnexion\":NULL";
							// pas de prêts de base
							jsonString += ",\"pret\":NULL";
							break;
						case "pret":
							System.out.println("Date de début ?");
							jsonString += ",\"dateDebut\":"+sc.nextLine();
							System.out.println("Durée ?");
							jsonString += ",\"duree\":"+sc.nextLine();
							break;
						case "categorie":
							System.out.println("Nom ?");
							jsonString += ",\"nom\":"+sc.nextLine();
							System.out.println("Description ?");
							jsonString += ",\"description\":"+sc.nextLine();
							break;
						default:
							break;
					}
					
					response = client.resource(uri)
					.path("catalogue")
					.path(table)
					.post(ClientResponse.class,jsonString);
					corpsRepHttp = response.getEntity(String.class);
					System.out.println(corpsRepHttp);
					break;
				case "2":
					response = client.resource(uri)
					.path("catalogue")
					.path(table+"s")
					.get(ClientResponse.class);
					corpsRepHttp = response.getEntity(String.class);
					System.out.println(corpsRepHttp);
					break;
				case "3":
					
					System.out.println("Quel numéro souhaitez vous modifier ?");
					
					jsonString = "{\"id\":";
					System.out.println("id ? ");
					jsonString += Integer.parseInt(sc.nextLine());
					switch(table) {
						case "auteur":
							System.out.println("à propos de ?");
							jsonString += ",\"aProposDe\":"+sc.nextLine();
							System.out.println("Nationalité ?");
							jsonString += ",\"nationalite\":"+sc.nextLine();
							System.out.println("Type d'auteur : PRINCIPAL ou COAUTEUR ?");
							jsonString += ",\"type\":"+sc.nextLine();
							break;
						case "livre":
							System.out.println("Titre ?");
							jsonString += ",\"titre\":"+sc.nextLine();
							System.out.println("Résumé ?");
							jsonString += ",\"resume\":"+sc.nextLine();
							System.out.println("Quantité ?");
							jsonString += ",\"quantite\":"+Integer.parseInt(sc.nextLine());
							System.out.println("ISBN ?");
							jsonString += ",\"isbn\":"+Integer.parseInt(sc.nextLine());
							System.out.println("Photo ?");
							jsonString += ",\"photo\":"+sc.nextLine();
							System.out.println("Date de publication ?");
							jsonString += ",\"datePublication\":"+sc.nextLine();
							System.out.println("Auteur ?");
							jsonString += ",\"auteur\":"+Integer.parseInt(sc.nextLine());
							System.out.println("Categorie ?");
							jsonString += ",\"categorie\":"+Integer.parseInt(sc.nextLine());
							// pas de prêts de base
							jsonString += ",\"pret\":NULL";
							break;
						case "utilisateur":
							System.out.println("Nom ?");
							jsonString += ",\"nomUtilisateur\":"+sc.nextLine();
							System.out.println("Mot de passe ?");
							jsonString += ",\"motDePasse\":"+sc.nextLine();
							// Compte actif de base
							jsonString += ",\"statutCompte\":ACTIF";
							// Derniere connexion null de base
							jsonString += ",\"derniereConnexion\":NULL";
							// pas de prêts de base
							jsonString += ",\"pret\":NULL";
							break;
						case "pret":
							System.out.println("Date de début ?");
							jsonString += ",\"dateDebut\":"+sc.nextLine();
							System.out.println("Durée ?");
							jsonString += ",\"duree\":"+sc.nextLine();
							break;
						case "categorie":
							System.out.println("Nom ?");
							jsonString += ",\"nom\":"+sc.nextLine();
							System.out.println("Description ?");
							jsonString += ",\"description\":"+sc.nextLine();
							break;
						default:
							break;
					}
					
					response = client.resource(uri)
					.path("catalogue")
					.path(table)
					.put(ClientResponse.class,jsonString);
					corpsRepHttp = response.getEntity(String.class);
					System.out.println(corpsRepHttp);
					break;
				case "4":
					
					System.out.println("Quel numéro souhaitez vous supprimer ?");
					
					response = client.resource(uri)
					.path("catalogue")
					.path(table)
					.delete(ClientResponse.class,sc.nextLine());
					corpsRepHttp = response.getEntity(String.class);
					System.out.println(corpsRepHttp);
					break;
				case "5":
					finishedSubMenu = true;
					break;
				default:
					System.out.println("Veuillez choisir parmi la liste de choix");
					break;
				}

			}
		}




		sc.close();
	}
}
