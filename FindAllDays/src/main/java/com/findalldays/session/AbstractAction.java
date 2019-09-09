package com.findalldays.session;

import java.io.Serializable;

import com.findalldays.util.Const;
import com.findalldays.util.FacesUtil;


public abstract class AbstractAction implements Serializable {
		private static final long serialVersionUID = 1L;
		
		public SessionBean getSessionBean() {
			SessionBean mBean = (SessionBean) getFacesUtils().getSessionMapValue(Const.ESessionBean.SESSION_BEAN);
			if (mBean == null) {
				mBean = new SessionBean();
			}
			return mBean;
		}

		public void setSessionBean(SessionBean sessionBean) {
			getFacesUtils().setSessionMapValue(Const.ESessionBean.SESSION_BEAN, sessionBean);
		}
		protected FacesUtil getFacesUtils() {
			return FacesUtil.getInstance();
		}
		
	
}
