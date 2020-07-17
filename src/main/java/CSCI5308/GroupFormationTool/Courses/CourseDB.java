package CSCI5308.GroupFormationTool.Courses;

import java.util.List;

import CSCI5308.GroupFormationTool.Database.CallStoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDB implements ICoursePersistence
{
	public List<Course> loadAllCourses()
	{
		List<Course> courses = new ArrayList<Course>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadAllCourses()");
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					long id = results.getLong(1);
					String title = results.getString(2);
					Course c = new Course();
					c.setId(id);
					c.setTitle(title);
					courses.add(c);
				}
			}
		}
		catch (SQLException e)
		{
			// Logging needed.
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return courses;
	}

	public void loadCourseByID(long id, Course course)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spFindCourseByID(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					String title = results.getString(2);
					course.setId(id);
					course.setTitle(title);
				}
			}
		}
		catch (SQLException e)
		{
			// Logging needed.
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
	}



	//Returns list of courses based on a userID
	public List<Course> loadCoursebyUser(long uid)
	{
		List<Course> courses = new ArrayList<Course>();
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spLoadCourseByUserID(?)");
			proc.setParameter(1, uid);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while (results.next())
				{
					String title = results.getString("title");
					long id=results.getLong("id");
					Course c = new Course();
					c.setId(id);
					c.setTitle(title);
					courses.add(c);
				}
			}
		}
		catch (SQLException e)
		{
			// Logging needed.
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return courses;
	}


    //Check survey published or not using courseID
	public boolean checkCourseSurvey(long id)
	{
		CallStoredProcedure proc = null;
		String s=new String();
		try
		{
			proc = new CallStoredProcedure("spCheckSurveyPublished(?)");
			proc.setParameter(1, id);
			ResultSet results = proc.executeWithResults();
			if (null != results)
			{
				while(results.next())
				{
					s = results.getString("isPublished");
				}
			}

			if(s.equalsIgnoreCase("y")||s.equalsIgnoreCase("Y"))
			{
				return true;
			}
		}
		catch (SQLException e)
		{
			// Logging needed.
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return false;

	}





	public boolean createCourse(Course course)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spCreateCourse(?, ?)");
			proc.setParameter(1, course.getTitle());
			proc.registerOutputParameterLong(2);
			proc.execute();
		}
		catch (SQLException e)
		{
			// Logging needed
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}


	public boolean deleteCourse(long id)
	{
		CallStoredProcedure proc = null;
		try
		{
			proc = new CallStoredProcedure("spDeleteCourse(?)");
			proc.setParameter(1, id);
			proc.execute();
		}
		catch (SQLException e)
		{
			// Logging needed
			return false;
		}
		finally
		{
			if (null != proc)
			{
				proc.cleanup();
			}
		}
		return true;
	}
}
