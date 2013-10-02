require libtool-${PV}.inc

DEPENDS = ""

PR = "${INC_PR}.1"
SRC_URI += "file://prefix.patch"

inherit native

EXTRA_OECONF = " --with-libtool-sysroot=${STAGING_DIR_NATIVE}"
CACHED_CONFIGUREVARS += "ac_cv_path_SED=/bin/sed"

do_configure_prepend () {
	# Remove any existing libtool m4 since old stale versions would break
	# any upgrade
	rm -f ${STAGING_DATADIR}/aclocal/libtool.m4
	rm -f ${STAGING_DATADIR}/aclocal/lt*.m4
}

do_install () {
	autotools_do_install
	install -d ${D}${bindir}/
	install -m 0755 ${HOST_SYS}-libtool ${D}${bindir}/${HOST_SYS}-libtool
}

