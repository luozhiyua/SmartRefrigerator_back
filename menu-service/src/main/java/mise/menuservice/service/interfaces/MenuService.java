package mise.menuservice.service.interfaces;

//import com.github.angel.raa.modules.dto.MenuDTO;
//import com.github.angel.raa.modules.utils.Response;
import lombok.NonNull;
import mise.menuservice.dto.InputDTO;
import mise.menuservice.dto.MenuDTO;
import mise.menuservice.utils.Response;

import java.util.List;

public interface MenuService {

    List<MenuDTO> getAllMenus();

    List<MenuDTO> getAvailableMenus(Long userId);

    List<MenuDTO> getMenusByCollect(Long userId);

    List<MenuDTO> getMenusByInput(InputDTO input);

    MenuDTO getMenuById(@NonNull Long id);

    Response saveMenu(@NonNull MenuDTO body);

    Boolean getMenuCollection(@NonNull Long id, Long userId);

    Response updateMenu(@NonNull Long id, MenuDTO body);

    Response collectMenu(@NonNull Long id, Long userId);
    Response uncollectMenu(@NonNull Long id, Long userId);

    Response deleteMenu(@NonNull Long id);

}
