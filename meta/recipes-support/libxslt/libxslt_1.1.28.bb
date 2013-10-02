DESCRIPTION = "GNOME XSLT library"
HOMEPAGE = "http://xmlsoft.org/XSLT/"
BUGTRACKER = "https://bugzilla.gnome.org/"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://Copyright;md5=0cd9a07afbeb24026c9b03aecfeba458"

SECTION = "libs"
DEPENDS = "libxml2"
PR = "r0"

SRC_URI = "ftp://xmlsoft.org/libxslt//libxslt-${PV}.tar.gz \
           file://pkgconfig_fix.patch"

SRC_URI[md5sum] = "9667bf6f9310b957254fdcf6596600b7"
SRC_URI[sha256sum] = "5fc7151a57b89c03d7b825df5a0fae0a8d5f05674c0e7cf2937ecec4d54a028c"
S = "${WORKDIR}/libxslt-${PV}"

inherit autotools pkgconfig binconfig lib_package

# We don't DEPEND on binutils for ansidecl.h so ensure we don't use the header
do_configure_prepend () {
	sed -i -e 's/ansidecl.h//' ${S}/configure.in
}

EXTRA_OECONF = "--without-python --without-debug --without-mem-debug --without-crypto"
# older versions of this recipe had ${PN}-utils
RPROVIDES_${PN}-bin += "${PN}-utils"
RCONFLICTS_${PN}-bin += "${PN}-utils"
RREPLACES_${PN}-bin += "${PN}-utils"

FILES_${PN} += "${libdir}/libxslt-plugins"
FILES_${PN}-dev += "${libdir}/xsltConf.sh"

BBCLASSEXTEND = "native"
