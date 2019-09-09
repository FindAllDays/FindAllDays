package com.ift.slip.ui;

 
import java.util.ArrayList;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import com.ift.imywork.util.Constant;



public class Pagination {
	private  int pageNo = 1;
	private  int pageCount = 0;
	private  int rowCount = 0;
	private int sizePerPage=5;
	public Object parent;
	private List<Integer> pageList;
	
	public Pagination(Object parent){
		this.parent = parent;
		pageList = new ArrayList<Integer>();
	
	}
	public void loadData() {
		
	}
	
	
	
	public void genPageList() {
		pageList = new ArrayList<Integer>();
//		pageNo = 5;
//		rowCount = 30;
//		pageCount = (int) Math.ceil(rowCount / getPageSize());

		pageList = new ArrayList<Integer>();
		if(pageCount>sizePerPage){
		
			for (int i=0;i<sizePerPage;i++) {
				if(pageNo<=3){
					pageList.add(i+1);
				}
				else if(pageNo>=pageCount-(sizePerPage/2)+1){
					 
						pageList.add(pageCount-((sizePerPage-1)-i));
					
				}
				else{
					if(i>=(sizePerPage/2)){
						pageList.add(pageNo+(i-(sizePerPage/2)));
					}
					else{
						pageList.add(pageNo-((sizePerPage/2)-i));
					}
				}
			}
		}
		else{
			for (int i=0;i<pageCount;i++) {
				pageList.add(i+1);
			}
		}
		
	}
	
	public void goToPage(int pageNo) {
		this.pageNo = pageNo;
		genPageList();
		loadData();
	}
	
	public void prev() {
		if(pageNo <= 1) {
			return;
		}
		
		pageNo--;
		genPageList();
		loadData();
	}
	
	public void prevFirst() {
		if(pageNo <= 1) {
			return;
		}
		
		pageNo = 1;
		genPageList();
		loadData();
		
	}
	
	public void pageChange(AjaxBehaviorEvent e ){
		if(pageNo > pageCount) {
			pageNo = pageCount;
		}
		
		if(pageNo < 1) {
			pageNo = 1;
		}
		genPageList();
		loadData();
	}
	
	public void next() {
		if(pageNo >= pageCount) {
			return;
		}

		pageNo++;
		loadData();
	}
	
	public void nextLast() {
		if(pageNo >= pageCount) {
			return;
		}
		
		pageNo = pageCount;
		loadData();
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
		genPageList();
		
	}
	public Object getParent() {
		return parent;
	}
	public void setParent(Object parent) {
		this.parent = parent;
	}
	public int getPageSize() {
		return Constant.pageSize;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public List<Integer> getPageList() {
		return pageList;
	}
	public void setPageList(List<Integer> pageList) {
		this.pageList = pageList;
	}
	public int getSizePerPage() {
		return sizePerPage;
	}
	public void setSizePerPage(int sizePerPage) {
		this.sizePerPage = sizePerPage;
	}
	
	
}
