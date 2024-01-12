package mise.menuservice.controller;


import lombok.RequiredArgsConstructor;
import mise.menuservice.dto.InputDTO;
import mise.menuservice.dto.MenuDTO;
import mise.menuservice.service.interfaces.MenuService;
import mise.menuservice.utils.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MenuController {
    private final MenuService menuService;
    @GetMapping("/test")
    public String test(){
        return "menu-service1";
    }

    @GetMapping("/all-menu")
    public ResponseEntity<List<MenuDTO>> getAllMenus() {
        return ResponseEntity.ok(menuService.getAllMenus());
    }

    //推荐菜谱
    @GetMapping("/recommend-menu/{userId}")
    public ResponseEntity<List<MenuDTO>> getRecommendMenus(@PathVariable(value = "userId") Long userId){
        return ResponseEntity.ok(menuService.getAvailableMenus(userId));
    }

    @GetMapping("/menu-by/{menuId}")
    public ResponseEntity<MenuDTO> getMenuById(@Valid @PathVariable(value = "menuId") @Min(1) Long menuId) {
        return ResponseEntity.ok(menuService.getMenuById(menuId));
    }

//    @GetMapping("/menus-by-input/{input}")
//    public ResponseEntity<List<MenuDTO>> getMenusByInput(@PathVariable(value = "input") String input){
//        return ResponseEntity.ok(menuService.getMenusByInput(input));
//    }
    @PostMapping("/menus-by-input")
    public ResponseEntity<List<MenuDTO>> getMenusByInput(@Valid @RequestBody InputDTO input){
       return ResponseEntity.ok(menuService.getMenusByInput(input));
    }



    @GetMapping("/menus-by-collect/{userId}")
    public ResponseEntity<List<MenuDTO>> getMenusByCollect(@PathVariable Long userId){
        return ResponseEntity.ok(menuService.getMenusByCollect(userId));
    }

    @GetMapping("/collection/{id}/{userId}")
    public ResponseEntity<Boolean> getMenuCollection(@PathVariable Long id,@PathVariable Long userId) {
        return ResponseEntity.ok(menuService.getMenuCollection(id, userId));
    }

    @PostMapping("/create-menu")
    public ResponseEntity<Response> createMenu(@Valid @RequestBody MenuDTO menu) {
        return ResponseEntity.status(HttpStatus.CREATED).body(menuService.saveMenu(menu));
    }

    @PutMapping("/update-menu/{id}")
    public ResponseEntity<Response> updateMenu(@Valid @RequestBody MenuDTO menu, @PathVariable Long id) {
        return ResponseEntity.ok(menuService.updateMenu(id, menu));
    }

    @PutMapping("/collect-menu/{id}/{userId}")
    public ResponseEntity<Response> collectMenu(@PathVariable Long id,@PathVariable Long userId) {
        return ResponseEntity.ok(menuService.collectMenu(id, userId));
    }

    @PutMapping("/uncollect-menu/{id}/{userId}")
    public ResponseEntity<Response> uncollectMenu(@PathVariable Long id,@PathVariable Long userId) {
        return ResponseEntity.ok(menuService.uncollectMenu(id, userId));
    }

    @DeleteMapping("/delete-menu/{id}")
    public ResponseEntity<Response> deleteMenu(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.deleteMenu(id));
    }
}
