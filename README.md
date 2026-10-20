# ScopenBreak
Standalone Fabric mod for Minecraft 26.3. Needs Fabric Loader >= 0.19.5, Fabric API and the
SecurityCraft (Fabric) port. SecurityCraft is not a compile dependency.

Before building:
1. Fill in `fabric_version` and `loom_version` in gradle.properties (https://fabricmc.net/develop).
2. Use JDK 25.
3. Verify the mixin target with:
   javap -p -cp SecurityCraft-fabric-V0.6.1.jar net.geforcemods.securitycraft.util.BlockUtils
   and edit BlockUtilsMixin to match.

Build: ./gradlew build   (wrapper not included: run `gradle wrapper` or copy it from the Fabric template)
Output: build/libs/scopenbreak-1.0.0.jar
