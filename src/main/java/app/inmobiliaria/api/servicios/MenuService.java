package app.inmobiliaria.api.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.inmobiliaria.api.entity.Menu;
import app.inmobiliaria.api.entity.Role;
import app.inmobiliaria.api.entity.SubMenu;
import app.inmobiliaria.api.payload.response.MenuRoleResponse;
import app.inmobiliaria.api.repository.MenuRepository;

@Service
public class MenuService {
	
	List<Role> listRole = new ArrayList<>();

	List<SubMenu> listsubMenu = new ArrayList<>();

	List<MenuRoleResponse> lista_menuRole = new ArrayList<>();

	MenuRoleResponse maObj = new MenuRoleResponse();

	List<MenuRoleResponse> myList = new ArrayList<>();

	Logger logger_2 = LogManager.getLogger();
	
	@Autowired
	private MenuRepository MenuRepo;

	@PersistenceContext
	EntityManager entityManager;


	public Menu create(Menu us) {
		return MenuRepo.save(us);
	}

	public Menu update(Menu us) {
		return MenuRepo.save(us);
	}

	public List<Menu> getAllMenu() {
		return MenuRepo.findAll();
	}

	public void delete(Menu us) {
		MenuRepo.delete(us);
	}

	public MenuRoleResponse search(Long id) {
		MenuRoleResponse menurole = new MenuRoleResponse();
		MenuRoleResponse padreMenu = new MenuRoleResponse();
		List<MenuRoleResponse> padreMenumyList = new ArrayList<>();
		List<Role> listRoles = new ArrayList<>();
		Role roles = new Role();
		
		Optional<Menu> Optionalmenu = MenuRepo.findById(id);
		
		if(Optionalmenu.isPresent()) {
			Menu menu = Optionalmenu.get();
			
			List<Menu> childMenu = MenuRepo.findByTipoMenu(id);
			
			List<MenuRoleResponse> childMenuList = new ArrayList<>();
			MenuRoleResponse menurolechild = new MenuRoleResponse();
			
			menurole.setMenu_id(menu.getMenu_id());
			menurole.setTipo_menu(menu.getTipo_menu());
			menurole.setArgumentos(menu.getArgumentos());
			menurole.setDescripcion(menu.getDescripcion());
			menurole.setMenu_icon(menu.getMenu_icon());
			menurole.setMenu_path(menu.getMenu_path());
			menurole.setMenu_title(menu.getMenu_title());
			
			menu.getRoles().stream().forEach((r)->{
				roles.setId(r.getId());
				roles.setName(r.getName());
				listRoles.add(roles);
			});
			
			menurole.setRoles(listRoles);  	
			menurole.setVigencia(menu.getVigencia());
			/*Set<Role> strRoles = menu.get(i).getRoles();*/
			
			Menu padremenuService = MenuRepo.findPadreByChild(menu.getTipo_menu());
			
			if(padremenuService != null) {
				padreMenu.setMenu_id(padremenuService.getMenu_id());
				padreMenu.setTipo_menu(padremenuService.getTipo_menu());
				padreMenu.setArgumentos(padremenuService.getArgumentos());
				padreMenu.setDescripcion(padremenuService.getDescripcion());
				padreMenu.setMenu_icon(padremenuService.getMenu_icon());
				padreMenu.setMenu_path(padremenuService.getMenu_path());
				padreMenu.setMenu_title(padremenuService.getMenu_title());
				menurole.setPadre(padreMenu);
			}
			

			padreMenumyList.add(menurole);
			
			childMenu.stream().forEach((s)-> {
				
				menurolechild.setMenu_id(s.getMenu_id());
				menurolechild.setTipo_menu(s.getTipo_menu());
				menurolechild.setArgumentos(s.getArgumentos());
				menurolechild.setDescripcion(s.getDescripcion());
				menurolechild.setMenu_icon(s.getMenu_icon());
				menurolechild.setMenu_path(s.getMenu_path());
				menurolechild.setMenu_title(s.getMenu_title());
				childMenuList.add(menurolechild);
			 });
			
			menurole.setSubmenu(childMenuList);
			
			return menurole;
		}
		
		return null;
		
	}
	
	public Optional<Menu> deleteSearch(Long id) {
		return MenuRepo.findById(id);
	}

	public void delete_menu(Long id) {
		MenuRepo.deleteById(id);
	}

	public Menu buscarMenu(Long id) {
		return MenuRepo.findOneById(id);
	}

	public List<Menu> getAllMenuRol(Long long1) {
		List<Menu> listaMenu = MenuRepo.findMenuByRol(long1);
		return listaMenu;
	}

	public List<Menu> getSubMenuByMenu (Long id){
		List<Menu> listaMenu = MenuRepo.findByTipoMenu(id);
		return listaMenu;
		
	}
	public List<MenuRoleResponse> obtenerMenu() {

		return MenuTree();

	}
	
	List<Menu> menu = new ArrayList<>();

	public List<MenuRoleResponse> obtenerMenuRol(Long rol_id) {

		return MenuTreeRol(rol_id);

	}
	
	

	public List<MenuRoleResponse> MenuTreeRol(Long rol_id) {
		
		if(lista_menuRole.size() > 0) {
			lista_menuRole.clear();
		}
		
		List<Menu> menu = MenuRepo.findMenuByRol(rol_id);

		for (int i = 0; i < menu.size(); i++) {
			MenuRoleResponse menurole = new MenuRoleResponse();
			menurole.setMenu_id(menu.get(i).getMenu_id());
			menurole.setTipo_menu(menu.get(i).getTipo_menu());
			menurole.setArgumentos(menu.get(i).getArgumentos());
			menurole.setDescripcion(menu.get(i).getDescripcion());
			menurole.setMenu_icon(menu.get(i).getMenu_icon());
			menurole.setMenu_path(menu.get(i).getMenu_path());
			menurole.setMenu_title(menu.get(i).getMenu_title());
			/*Set<Role> strRoles = menu.get(i).getRoles();
			menurole.setRoles((List<Role>) strRoles);*/  	
			menurole.setVigencia(menu.get(i).getVigencia());
			
			lista_menuRole.add(menurole);
		}

		return builTree();
	}

	public List<MenuRoleResponse> MenuTree() {
		
		if(lista_menuRole.size() > 0) {
			lista_menuRole.clear();
		}
		
		List<Menu> menu = MenuRepo.findAll();

		for (int i = 0; i < menu.size(); i++) {
			MenuRoleResponse menurole = new MenuRoleResponse();
			menurole.setMenu_id(menu.get(i).getMenu_id());
			menurole.setTipo_menu(menu.get(i).getTipo_menu());
			menurole.setArgumentos(menu.get(i).getArgumentos());
			menurole.setDescripcion(menu.get(i).getDescripcion());
			menurole.setMenu_icon(menu.get(i).getMenu_icon());
			menurole.setMenu_path(menu.get(i).getMenu_path());
			menurole.setMenu_title(menu.get(i).getMenu_title());
			menurole.setVigencia(menu.get(i).getVigencia());
			lista_menuRole.add(menurole);
		}
		return builTree();
	}

	// Establish tree structure
	public List<MenuRoleResponse> builTree() {
		List<MenuRoleResponse> treeMenus = new ArrayList<MenuRoleResponse>();
		logger_2.info("obteniendo los Nodos desde builTree");
		logger_2.info(getRootNode());
		for (MenuRoleResponse menuNode : getRootNode()) {
			menuNode = buildChilTree(menuNode);
			treeMenus.add(menuNode);
		}

		return treeMenus;
	}

	private List<MenuRoleResponse> getRootNode() {

		List<MenuRoleResponse> rootMenuLists = new ArrayList<MenuRoleResponse>();

		for (MenuRoleResponse menuNode : lista_menuRole) {
			
			if (menuNode.getTipo_menu() == 0) {
				logger_2.info("obteniendo el tipoMenu");	
				logger_2.info(menuNode.getTipo_menu());
				
				menuNode.setTipo("menu");
				menuNode.setMenu_path_response(menuNode.getMenu_path());
				rootMenuLists.add(menuNode);

			}
		}
		logger_2.info("obteniendo los Nodos");
		logger_2.info(rootMenuLists);
		return rootMenuLists;
	}

	// Recursion, building subtree structure
	private MenuRoleResponse buildChilTree(MenuRoleResponse pNode) {
		List<MenuRoleResponse> chilMenus = new ArrayList<MenuRoleResponse>();
		logger_2.info(pNode);
		for (MenuRoleResponse menuNode : lista_menuRole) {
		
			if (menuNode.getTipo_menu().equals(pNode.getMenu_id())) {
				menuNode.setMenu_path_response(pNode.getMenu_path() + menuNode.getMenu_path());
				menuNode.setTipo("submenu");
				chilMenus.add(buildChilTree(menuNode));
			}
		}
		
		pNode.setSubmenu(chilMenus);
		return pNode;
	}

}

