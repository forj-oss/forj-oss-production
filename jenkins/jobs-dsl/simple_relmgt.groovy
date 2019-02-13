
multibranchPipelineJob('simple-relmgt') {
  description('Folder for Project simple-relmgt generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          repoOwner('forj-oss')
          scanCredentialsId('github-user')
          repository('simple-relmgt')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
