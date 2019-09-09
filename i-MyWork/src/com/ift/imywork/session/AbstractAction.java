package com.ift.imywork.session;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.ift.imywork.constant.Constant;
import com.ift.imywork.util.FacesUtil;






public abstract class AbstractAction implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public SessionBean getSessionBean() {
			SessionBean mBean = (SessionBean) getFacesUtils().getSessionMapValue(Constant.ESessionBean.SESSION_BEAN);
			if (mBean == null) {
				mBean = new SessionBean();
			}
			return mBean;
		}

		public void setSessionBean(SessionBean sessionBean) {
			getFacesUtils().setSessionMapValue(Constant.ESessionBean.SESSION_BEAN, sessionBean);
		}
		protected FacesUtil getFacesUtils() {
			return FacesUtil.getInstance();
		}
		
}
