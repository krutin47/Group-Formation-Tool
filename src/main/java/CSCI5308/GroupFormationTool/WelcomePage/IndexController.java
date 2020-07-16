package CSCI5308.GroupFormationTool.WelcomePage;

import CSCI5308.GroupFormationTool.Courses.Course;
import CSCI5308.GroupFormationTool.Courses.ICoursePersistence;
import CSCI5308.GroupFormationTool.SystemConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController
{

	@GetMapping("/")
	public String greeting(Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		SystemConfig.instance().getLOG().debug("Checking user is authenticated -- " + authentication.isAuthenticated());
		if (authentication.isAuthenticated())
		{
			SystemConfig.instance().getLOG().info("User is already authenticated");
			ICoursePersistence courseDB = SystemConfig.instance().getCourseDB();
			List<Course> allCourses = courseDB.loadAllCourses();
			SystemConfig.instance().getLOG().debug("Loaded courses size -- " + allCourses.size());

			model.addAttribute("courses", allCourses);
		}
		return "index";
	}
}