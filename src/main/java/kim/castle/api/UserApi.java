package kim.castle.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.querydsl.core.types.Predicate;

import io.swagger.annotations.ApiOperation;
import kim.castle.busi.user.User;
import kim.castle.busi.user.UserService;

@RestController
@RequestMapping("/api/user")
public class UserApi {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "用户列表", notes = "分页数据")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Page<User> doPage(Predicate predicate, Pageable pageable) {
		return userService.findAll(predicate, pageable);
	}

}
