package com.capco.travel.custom.exception;

public class TravelException {

	public static class TravelServiceException extends BaseException {
		private static final long serialVersionUID = 1L;

		public TravelServiceException(String msg) {
			super(msg);
		}

		public TravelServiceException(Throwable msg) {
			super(msg);
		}
	}

	public static class DAOException extends BaseException {
		private static final long serialVersionUID = 1L;

		public DAOException(String msg) {
			super(msg);
		}

		public DAOException(Throwable msg) {
			super(msg);
		}
	}

	public static class ParsingException extends BaseException {
		private static final long serialVersionUID = 1L;

		public ParsingException(String msg) {
			super(msg);
		}

		public ParsingException(Throwable msg) {
			super(msg);
		}
	}

}
