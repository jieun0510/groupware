package com.human.springboot;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.human.springboot.dao.JieunDAO;
import com.human.springboot.dto.DepartmentDTO;
import com.human.springboot.dto.EmpDepartPositionDTO;
import com.human.springboot.dto.EmployeeDTO;
import com.human.springboot.dto.PositionDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class JeController {

	@Autowired
	private JieunDAO JiDao;
	
// ������ ���� (Ǯ����)
	
	// ��� ��ȸ ( ������ ���� )
	@GetMapping("/employee/inquiry")
	public String employeeInquiry() {
		return "employee/employee_inquiry";
	}
	// ��� �μ�/���� ���� ( ������ ���� )
	@GetMapping("/employee/registration")
	public String employeeRegistration() {
		return "employee/employee_registration";
	}
	// ����/�μ� ��� ( ������ ���� )
	@GetMapping("/employee/organization")
	public String employeeOrganization() {
		return "employee/employee_organization";
	}
	// ���� ��Ȳ ( ������ ���� )
	@GetMapping("/attendance/current")
	public String attendanceCurrent() {
		return "attendance/attendance_current";
	}
	// ���� ���� ( ������ ���� )
	@GetMapping("/attendance/management")
	public String attendanceManagement() { 
		return "attendance/attendance_management";
	}
	// ����� ���� ��Ȳ
	@GetMapping("/attendance/byEmployee")
	public String attendanceByEmployee() {
		return "attendance/attendance_ByEmployee";
	}
	
	// ��� ���� ���� ( update )
	@PostMapping("/employee_update0")
	@ResponseBody
	public String doEmpUpdate(HttpServletRequest req) {
		
	    String retval = "ok";
	    try {
	    	String emp_join=req.getParameter("emp_join");
	    	int emp_position=Integer.parseInt(req.getParameter("emp_position"));
	    	int emp_depart=Integer.parseInt(req.getParameter("emp_depart"));
			String emp_id=req.getParameter("emp_id");
			
			JiDao.employee_update0(emp_join, emp_position,emp_depart ,emp_id);
	    } catch (Exception e) {
	    	e.printStackTrace();
	        retval = "fail";
	    }
	    return retval;
	}
	
	// ��ٽð� �Է� ( update )
	@PostMapping("/attendance_end_id")
	@ResponseBody
	public String doAtt_end_id(HttpServletRequest req) {
		String retval = "ok";
	try {
		String emp_no = req.getParameter("emp_no");
		int param = Integer.parseInt(req.getParameter("param"));
		String night_time = req.getParameter("night_time");
		System.out.println("print param result : "+param);
		JiDao.attendance_end_id(param, emp_no, night_time);
	} catch(Exception e) {
		retval = "fail";
		e.printStackTrace();
	}
	return retval;
	}
// select option ����
	// ��� ���� �߰� ( �μ��� �ҷ�����, select )
	@PostMapping("/department_select0")
	@ResponseBody
	public String doDepSelect() {			
		ArrayList<DepartmentDTO> department_select0 = JiDao.department_select0();
		JSONArray ja = new JSONArray();
		for(int i=0; i<department_select0.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("dep_name", department_select0.get(i).getDep_name());
			jo.put("dep_manager", department_select0.get(i).getDep_manager());
			jo.put("dep_parent", department_select0.get(i).getDep_parent());
			jo.put("dep_id", department_select0.get(i).getDep_id());
			ja.put(jo);
		}
		return ja.toString();
	}
	// ��� ���� �߰� ( ���޸� �ҷ�����, select )
	@PostMapping("/position_select0")
	@ResponseBody
	public String doPosSelect() {
		ArrayList<PositionDTO> position_select0 = JiDao.position_select0();
		JSONArray ja = new JSONArray();
		for(int i=0; i<position_select0.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("position_name",position_select0.get(i).getPosition_name());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	// ��� ���� �߰� ( ������� �ҷ�����, select )
	@PostMapping("/form_select0")
	@ResponseBody
	public String doFormSelect() {
		ArrayList<PositionDTO> form_select0 = JiDao.form_select0();
		JSONArray ja = new JSONArray();
		for(int i=0; i<form_select0.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("job_type",form_select0.get(i).getJob_type());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	// ������ ( ������+�̸��� �ҷ�����, select )
	@PostMapping("/attendance_employee0")
	@ResponseBody
	public String doAttenEmployee() {
		ArrayList<EmployeeDTO> attendance_employee0 = JiDao.attendance_employee0();
		JSONArray ja = new JSONArray();
		for(int i=0; i<attendance_employee0.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("emp_name", attendance_employee0.get(i).getEmp_name());
			jo.put("emp_email", attendance_employee0.get(i).getEmp_email());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	// ��� ��ȸ ( �˻� �� �ҷ�����, select )
	@PostMapping("/employee_search")
	@ResponseBody
	public String doAllSelectend(HttpServletRequest req) {
		String emp_name = req.getParameter("emp_name");
		String emp_mobile = req.getParameter("emp_mobile");
		String emp_email = req.getParameter("emp_email");
		String dep_name = req.getParameter("dep_name");
		String position_name = req.getParameter("position_name");
		ArrayList<EmpDepartPositionDTO> employee_search =
		JiDao.employee_search(emp_name, emp_mobile, emp_email,dep_name,position_name);
		JSONArray ja = new JSONArray();
		for(int i=0; i<employee_search.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("position_name", employee_search.get(i).getPosition_name());
			jo.put("job_type", employee_search.get(i).getJob_type());
			jo.put("emp_name", employee_search.get(i).getEmp_name());
			jo.put("emp_mobile", employee_search.get(i).getEmp_mobile());
			jo.put("emp_email", employee_search.get(i).getEmp_email());
			jo.put("emp_gender", employee_search.get(i).getEmp_gender());
			jo.put("dep_name", employee_search.get(i).getDep_name());
			jo.put("emp_id", employee_search.get(i).getEmp_id());
			jo.put("emp_img", employee_search.get(i).getEmp_img());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	// ����� ���� ��ȸ ( �˻� �� �ҷ�����, select )
	@PostMapping("/attendance_list")
	@ResponseBody
	public String doAttList(HttpServletRequest req) {
		String dep_name = req.getParameter("dep_name");
		String attend_date = req.getParameter("attend_date");
		ArrayList<EmpDepartPositionDTO> attendance_list = JiDao.attendance_list(attend_date,dep_name);
		
		JSONArray ja = new JSONArray();
		for(int i=0; i<attendance_list.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("emp_name", attendance_list.get(i).getEmp_name());
			jo.put("dep_name", attendance_list.get(i).getDep_name());
			jo.put("position_name", attendance_list.get(i).getPosition_name());
			jo.put("start_time", attendance_list.get(i).getStart_time());
			jo.put("end_time", attendance_list.get(i).getEnd_time());
			jo.put("night_time", attendance_list.get(i).getNight_time());
			jo.put("tardy_time", attendance_list.get(i).getTardy_time());
			
			ja.put(jo);
		}
		return ja.toString();
	}
	
	// �ް� üũ ( select )
	@PostMapping("/leave_select_list")
	@ResponseBody
	public String doleaveList(HttpServletRequest req) {
		String dep_name = req.getParameter("dep_name");
		String attend_date = req.getParameter("attend_date");
		System.out.println("dep_name(leave) print : "+ dep_name);
		System.out.println("attend_date(leave) print :"+ attend_date);
		ArrayList<EmpDepartPositionDTO> leave_select_list = JiDao.leave_select_list(attend_date, dep_name);
		JSONArray ja = new JSONArray();
		for(int i=0; i<leave_select_list.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("emp_name", leave_select_list.get(i).getEmp_name());
			jo.put("dep_name", leave_select_list.get(i).getDep_name());
			jo.put("position_name", leave_select_list.get(i).getPosition_name());
			jo.put("leave_start", leave_select_list.get(i).getLeave_start());
			jo.put("leave_end", leave_select_list.get(i).getLeave_end());
			ja.put(jo);
		}
		return ja.toString();
	}
	// ������ �μ��̸� �ҷ�����
	@PostMapping("/all_organization")
	@ResponseBody
	public String doDepartment_ul() {
		ArrayList<EmpDepartPositionDTO> all_organization = JiDao.all_organization();
		JSONArray ja = new JSONArray();
		for(int i=0; i<all_organization.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("dep_name", all_organization.get(i).getDep_name());
			jo.put("emp_name",all_organization.get(i).getEmp_name());
			jo.put("position_name",all_organization.get(i).getPosition_id());
			ja.put(jo);
		}
		return ja.toString();
	}

	// ���� ���� �ҷ�����
	@PostMapping("/employeeData_select")
	@ResponseBody
	public String doEmployeeData_select(HttpServletRequest req) {
		String emp_id = req.getParameter("emp_id");
		System.out.println(emp_id);
		ArrayList<EmployeeDTO> employeeData_select = JiDao.employeeData_select(emp_id);
		JSONArray ja = new JSONArray();
		for(int i=0; i<employeeData_select.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("emp_name", employeeData_select.get(i).getEmp_name());
			jo.put("emp_mobile", employeeData_select.get(i).getEmp_mobile());
			jo.put("emp_email", employeeData_select.get(i).getEmp_email());
			jo.put("emp_id", employeeData_select.get(i).getEmp_id());
			jo.put("emp_img", employeeData_select.get(i).getEmp_img());
			ja.put(jo);
		}
		return ja.toString();
	}
	// ������� ���� id �̱�
	@PostMapping("/exemploye_select1")
	@ResponseBody
	public String doExEmployee(HttpServletRequest req) {
		String position_name = req.getParameter("position_name");
		String job_type = req.getParameter("job_type");
			ArrayList<PositionDTO> exemploye_select1 = JiDao.exemploye_select1(position_name, job_type);
			JSONArray ja = new JSONArray();
			for(int i=0; i<exemploye_select1.size(); i++) {
				JSONObject jo = new JSONObject();
				jo.put("position_id", exemploye_select1.get(i).getPosition_id());
				ja.put(jo);
			}
			return ja.toString();
}
	// ���� ��ȣ �̱�
	@PostMapping("/id_load_select")
	@ResponseBody
	public String doLoad_id(HttpServletRequest req) {
		String emp_id = req.getParameter("emp_id");
		ArrayList<EmployeeDTO> id_load_select = JiDao.id_load_select(emp_id);
		JSONArray ja = new JSONArray();
		for(int i=0; i<id_load_select.size(); i++) {
			JSONObject jo = new JSONObject();
			jo.put("emp_no", id_load_select.get(i).getEmp_no());
			ja.put(jo);
		}
		return ja.toString();
	}
	
	// �μ� id �̱�
		@PostMapping("/exemployee_select2")
		@ResponseBody
		public String doExEmployeeDep(HttpServletRequest req) {
			String dep_name = req.getParameter("dep_name");
				ArrayList<DepartmentDTO> exemployee_select2 = JiDao.exemployee_select2(dep_name);
				JSONArray ja = new JSONArray();
				for(int i=0; i<exemployee_select2.size(); i++) {
					JSONObject jo = new JSONObject();
					jo.put("dep_id", exemployee_select2.get(i).getDep_id());
					ja.put(jo);
				}
				return ja.toString();
	}

	// �μ� �߰� insert
		@PostMapping("/department_insert")
		@ResponseBody
		public String personorder(HttpServletRequest req) {
			String retval="ok";
			String dep_name = req.getParameter("dep_name");
			int dep_parent = Integer.parseInt(req.getParameter("dep_parent"));
			int dep_manager = Integer.parseInt(req.getParameter("dep_manager"));
			try {
				System.out.println(dep_name);
				System.out.println(dep_parent);
				System.out.println(dep_manager);
				JiDao.department_insert(dep_name,dep_parent, dep_manager);
			} catch(Exception e) {
				retval = e.getMessage();
				e.printStackTrace();
			}
			return retval;
		}
		
	// ���� �߰� ( insert )
		@PostMapping("/position_insert")
		@ResponseBody
		public String doPositionInsert(HttpServletRequest req) {
			String retval = "ok";
			String position_name = req.getParameter("position_name");
			String job_type = req.getParameter("job_type");
			try {
				JiDao.position_insert(position_name, job_type);
			} catch(Exception e) {
				retval = e.getMessage();
				e.printStackTrace();
			}
			return retval;
		}
	
	// ��ٽð� �Է�
		@PostMapping("/attendance_start_id")
		@ResponseBody
		public String doStart_emp(HttpServletRequest req) {
			String retval="ok";
			String emp_no = req.getParameter("emp_no");
			try {
				JiDao.attendance_start_id(emp_no);
			} catch(Exception e) {
				retval = e.getMessage();
			}
			return retval;
		}
	// ��� 1ȸ ����
		@PostMapping("/attendance_chack")
		@ResponseBody
		public String doEmp_idc(HttpServletRequest req) {
			String retval = "ok";
			int EmpCount = 0;
			int emp_no =  Integer.parseInt(req.getParameter("emp_no"));
			String attend_date = req.getParameter("attend_date");
			String emp_id = req.getParameter("emp_id");
			System.out.println(emp_no);
			System.out.println(attend_date);
			System.out.println(emp_id);
			EmpCount = JiDao.attendance_chack(emp_no, attend_date);
			System.out.println(EmpCount);
			try {
					if(emp_id =="") {
						retval = "2";
						System.out.println("2 go"+emp_id);
					} else {
						if(EmpCount == 0) {
							retval = "0";
						}else {
							retval = "1";
						}
					}
					
			} catch (Exception e) {
				throw new RuntimeException(e);
				
			}
			return retval;
			
		}
		
	// �μ� ���� ( delete )
		 @PostMapping("/department_delete")
		 @ResponseBody
		 public String docartdelete(HttpServletRequest req) { 
			 String retval="ok";
			 int dep_id = Integer.parseInt(req.getParameter("dep_id"));
			 
			 try {
					 JiDao.department_delete(dep_id);
			} catch(Exception e) {
				retval="fail";
			} 
			 return retval;
		}
	// ���� ���� ( delete )
		 @PostMapping("/position_delete")
		 @ResponseBody
		 public String doposition_Delete(HttpServletRequest req) { 
			 String retval="ok";
			 int position_id = Integer.parseInt(req.getParameter("position_id"));
			 
			 try {
					 JiDao.position_delete(position_id);
			} catch(Exception e) {
				retval="fail";
			} 
			 return retval;
		}
	// ���� ���� ( select )
		 @PostMapping("/position_name_select")
		 @ResponseBody
		 public String doPosition_Select() {
			 ArrayList<PositionDTO>position_name_select = JiDao.position_name_select();
			 JSONArray ja = new JSONArray();
			 for(int i= 0; i<position_name_select.size(); i++) {
				 JSONObject jo = new JSONObject();
				 jo.put("position_id", position_name_select.get(i).getPosition_id());
				 jo.put("position_name", position_name_select.get(i).getPosition_name());
				 jo.put("job_type", position_name_select.get(i).getJob_type());
				 ja.put(jo);
			 }
			 return ja.toString();
		 }
	// �μ��� ���� ( select )
		 @PostMapping("/dep_boss_name_select")
		 @ResponseBody
		 public String doDepBoss_name() {
				ArrayList<EmpDepartPositionDTO> dep_boss_name_select = JiDao.dep_boss_name_select();
				JSONArray ja = new JSONArray();
				for(int i=0; i<dep_boss_name_select.size(); i++) {
					JSONObject jo = new JSONObject();
					jo.put("emp_no", dep_boss_name_select.get(i).getEmp_no());
					jo.put("emp_name", dep_boss_name_select.get(i).getEmp_name());
					jo.put("position_id", dep_boss_name_select.get(i).getPosition_id());
					jo.put("position_name", dep_boss_name_select.get(i).getPosition_name());
					ja.put(jo);
				}
				return ja.toString();
	}
}

