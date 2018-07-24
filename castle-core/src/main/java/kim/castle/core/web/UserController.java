package kim.castle.core.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;

import kim.castle.core.support.mvc.Action;
import kim.castle.core.user.User;
import kim.castle.core.user.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return "/user";
	}

	@RequestMapping(value = { "/add", "/edit" }, method = RequestMethod.GET)
	public String add(Model model) {
		return edit(new User(), model);
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") User user, Model model) {
		model.addAttribute("user", user);
		return "/user_edit";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<User> doPage(Predicate predicate, Pageable pageable) {
		return userService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Action doSave(@ModelAttribute @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Action.validateError(bindingResult.getAllErrors());
		}

		userService.save(user);

		return Action.successAlert();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Action doBatchDelete(@RequestParam(value = "ids[]") User[] users) {
		for (User user : users) {
			userService.delete(user);
		}
		return Action.successAlert();
	}

}