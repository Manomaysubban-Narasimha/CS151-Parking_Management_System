FROM eclipse-temurin:17

WORKDIR /Project

COPY * .

RUN ls

CMD ["/usr/bin/env", "/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home/bin/java", "-XX:+ShowCodeDetailsInExceptionMessages", "@/var/folders/pd/f2v_qxr15wv98x8mtv1n0w20snrbd_/T/cp_95qivjh30n1sgd2x2v46teyxi.argfile", "com.classProject.project.Cs151ParkingManagementSystemApplication"]